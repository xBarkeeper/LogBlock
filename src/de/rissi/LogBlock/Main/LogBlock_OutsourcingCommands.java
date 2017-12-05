package de.rissi.LogBlock.Main;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.rissi.LogBlock.Database.Databse_Utils;

public class LogBlock_OutsourcingCommands implements CommandExecutor
{

	@SuppressWarnings("unused")
	private LogBlock plugin;

	public LogBlock_OutsourcingCommands(LogBlock plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player p = (Player) sender;

		String playerCoords = p.getLocation().getBlockX() + "," + (p.getLocation().getBlockY() - 1) + ","
				+ p.getLocation().getBlockZ();
		p.sendMessage(playerCoords);
		ArrayList<ResultSet> results = Databse_Utils.getInormation("SELECT * FROM " + LogBlock_Values.DATABASE_DBNAME
				+ ".block WHERE Block_Coords = '" + playerCoords + "'");

		try
		{
			for (int i = 0; i < results.size(); i++)
			{
				@SuppressWarnings("deprecation")
				Player blockBreakPlayer = Bukkit.getPlayer(results.get(i).getString("Player_UUID"));
				
				Date tg = new Date(results.get(i).getTimestamp("column datetime").getTime());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
				String time = sdf.format(tg);
				
				p.sendMessage("Player " + blockBreakPlayer.getName() + " zerstörte hier am " + time);
			}
		} catch (Exception exc)
		{
			exc.printStackTrace();
		}
		return true;
	}

	public String[] shortArgs(String[] args) {
		String[] newArgs = new String[(args.length - 1)];
		for (int i = 1; i < args.length; i++)
		{
			newArgs[i - 1] = args[i];
		}
		return newArgs;
	}

}
