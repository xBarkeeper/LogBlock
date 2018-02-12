package de.rissi.LogBlock.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LogBlock_HelpCommand
{

	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		p.sendMessage("pong");
		

		return true;
	}

}
