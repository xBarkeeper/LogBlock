package de.rissi.LogBlock;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
		
		p.sendMessage("No Commands avaible at the moment");
	
		return true;
	}
	
	public String[] shortArgs(String[] args) {
		String[] newArgs = new String[ (args.length-1) ];
		for(int i=1; i<args.length; i++) {
			newArgs[i-1] = args[i];
		}
		return newArgs;
	}

}
