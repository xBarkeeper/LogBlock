package de.rissi.LogBlock.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LogBlock_HelpCommand
{

	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		p.sendMessage("LogBlock Hilfe:");
		p.sendMessage("/lb gui ::: Enter/Leave Editor Mode");
		p.sendMessage("/lb history ::: Get the History of a Coord");
		p.sendMessage("/lb reset [] [] ::: Reset Area:");
		

		return true;
	}

}
