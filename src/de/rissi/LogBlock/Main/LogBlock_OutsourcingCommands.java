package de.rissi.LogBlock.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.rissi.LogBlock.Commands.*;

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
		
		switch(args[0].toLowerCase()) {
			case LogBlock_Values.BLOCKBREAKHISTORYCOMMAND:
				LogBlock_BlockBreakHistoryCommand.onCommand(sender, cmd, label, shortArgs(args));
				break;
			case LogBlock_Values.HELPCOMMAND:
				LogBlock_HelpCommand.onCommand(sender, cmd, label, shortArgs(args));
				break;
			case LogBlock_Values.RESETAREACOMMAND:
				LogBlock_ResetAreaCommand.onCommand(sender, cmd, label, shortArgs(args));
				break;
			case LogBlock_Values.SETAREACOMMAND:
				LogBlock_SetAreaCommand.onCommand(sender, cmd, label, shortArgs(args));
				break;
			case LogBlock_Values.EDITMODECOMMAND:
				LogBlock_EditModeCommand.onCommand(sender, cmd, label, shortArgs(args));
				break;
			default:
					p.sendMessage(LogBlock_Values.WRONGCOMMANDUSE);
					break;
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
