package de.rissi.LogBlock.Commands;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.rissi.LogBlock.Database.Databse_Utils;
import de.rissi.LogBlock.Main.LogBlock_Values;

public class LogBlock_BlockBreakHistoryCommand
{

	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		String playerCoords = p.getLocation().getBlockX() + "," + (p.getLocation().getBlockY() - 1) + ","
				+ p.getLocation().getBlockZ();

		try
		{
			Databse_Utils.connection = Databse_Utils.MySql.openConnection();
			Databse_Utils.statement = Databse_Utils.connection.createStatement();

			ResultSet res = Databse_Utils.statement
					.executeQuery("SELECT * FROM " + LogBlock_Values.DATABASE_DBNAME + "." +LogBlock_Values.DATABASE_TABLE_BLOCK + " WHERE "
							+ LogBlock_Values.COLUMNNAME_BLOCK_COORDS + " = '" + playerCoords + "'");
			while (res.next())
			{
				Date tg = new Date(res.getTimestamp(LogBlock_Values.COLUMNNAME_BLOCK_EDITTIME).getTime());
				SimpleDateFormat sdf = new SimpleDateFormat(LogBlock_Values.TIMEFORMAT);
				String[] time = sdf.format(tg).split(" ");

				p.sendMessage(
						Bukkit.getOfflinePlayer(UUID.fromString(res.getString(LogBlock_Values.COLUMNNAME_PLAYER_UUID)))
								.getName() + " zerstörte hier am " + time[0] + " um " + time[1] + " den Block "
								+ res.getString(LogBlock_Values.COLUMNNAME_BLOCK_TYP));
			}

			Databse_Utils.connection.close();
		} catch (Exception exc)
		{
			exc.printStackTrace();
		}
		return true;
	}

}
