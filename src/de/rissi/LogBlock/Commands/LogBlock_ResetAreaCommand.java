package de.rissi.LogBlock.Commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.rissi.LogBlock.Database.Databse_Utils;
import de.rissi.LogBlock.Main.LogBlock_Values;

public class LogBlock_ResetAreaCommand
{

	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(!(args.length == 1 || args.length == 2)) {
			p.sendMessage(LogBlock_Values.WRONGCOMMANDUSE);
			return true;
		}

		Integer[] pos1 = LogBlock_Values.areaHashMap.get("1:" + p.getUniqueId());
		Integer[] pos2 = LogBlock_Values.areaHashMap.get("2:" + p.getUniqueId());

		boolean[] oneIsBigger = new boolean[3];
		Integer[] dif = new Integer[3];
		Integer[] begin = new Integer[3];

		for (int i = 0; i < 3; i++)
		{
			if (pos1[i] < pos2[i])
			{
				dif[i] = pos2[i] - pos1[i];
				oneIsBigger[i] = false;
				begin[i] = pos1[i];
			} else
			{
				dif[i] = pos1[i] - pos2[i];
				oneIsBigger[i] = true;
				begin[i] = pos2[i];
			}
		}
		
			try
			{
				Databse_Utils.connection = Databse_Utils.MySql.openConnection();
				Databse_Utils.statement = Databse_Utils.connection.createStatement();

				for (int y = begin[1]; y < (begin[1] + dif[1] + 1); y++)
				{
					for (int x = begin[0]; x < (begin[0] + dif[0] + 1); x++)
					{
						for (int z = begin[2]; z < (begin[2] + dif[2] + 1); z++)
						{
							resetBlocks(x, y, z, args);
						}
					}
				}

				Databse_Utils.connection.close();
			} catch (Exception exc)
			{
				exc.printStackTrace();
			}

		return true;
	}

	@SuppressWarnings("deprecation")
	public static void resetBlocks(int x, int y, int z, String[] args) throws SQLException
	{
		ResultSet res;
		if (args.length == 1)
		{
			String time[] = LogBlock_Values.getCurrentTimeStamp().split(" ");
			res = Databse_Utils.statement.executeQuery("SELECT * FROM " + LogBlock_Values.DATABASE_DBNAME + "."
					+ LogBlock_Values.DATABASE_TABLE_BLOCK + " WHERE " + LogBlock_Values.COLUMNNAME_BLOCK_COORDS
					+ " = '" + x + "," + y + "," + z + "' and " + LogBlock_Values.COLUMNNAME_BLOCK_EDITTIME + " >= '"
					+ time[0] + " " + args[0] + "'");
		} else
		{
			res = Databse_Utils.statement.executeQuery("SELECT * FROM " + LogBlock_Values.DATABASE_DBNAME + "."
					+ LogBlock_Values.DATABASE_TABLE_BLOCK + " WHERE " + LogBlock_Values.COLUMNNAME_BLOCK_COORDS
					+ " = '" + x + "," + y + "," + z + "' and " + LogBlock_Values.COLUMNNAME_BLOCK_EDITTIME + " >= '"
					+ args[0] + " " + args[1] + "'");
		}
		while (res.next())
		{
			if (res.isFirst())
			{
				Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
				Block block = loc.getBlock();
				if (res.getString(LogBlock_Values.COLUMNNAME_BLOCK_ACTION).equals(LogBlock_Values.BREAK))
				{
					String[] blockValue = res.getString(LogBlock_Values.COLUMNNAME_BLOCK_TYP).split(":");
					block.setTypeIdAndData(Integer.parseInt(blockValue[0]), Byte.parseByte(blockValue[1]), true);
				} else
				{
					block.setType(Material.AIR);
				}
				break;
			}
		}
	}
	
}
