package de.rissi.LogBlock.Commands;

import java.sql.ResultSet;

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

						ResultSet res = Databse_Utils.statement
								.executeQuery("SELECT * FROM " + LogBlock_Values.DATABASE_TABLE_BLOCKBREAK
										+ " WHERE " + LogBlock_Values.COLUMNNAME_BLOCK_COORDS + " = '" + x + "," + y + "," + z
										+ "' and " + LogBlock_Values.COLUMNNAME_BLOCK_EDITTIME + " >= '" + args[0] + " " + args[1] + "'");
						System.out.println("Blockupdate: " + x + " " + y + " " + z);
						while (res.next())
						{
							System.out.println("Hallo2");
							if (res.isLast())
							{
								System.out.println("Hallo");
								Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
								Block block = loc.getBlock();
								block.setType(Material.getMaterial((res.getString(LogBlock_Values.COLUMNNAME_BLOCK_TYP))));
								System.out.println(res.getString(LogBlock_Values.COLUMNNAME_BLOCK_TYP) );
							}
						}
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

}
