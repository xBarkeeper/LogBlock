package de.rissi.LogBlock.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.rissi.LogBlock.Main.LogBlock_Values;

public class LogBlock_SetAreaCommand
{

	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		Integer[] position =
		{ p.getLocation().getBlockX(), (p.getLocation().getBlockY() - 1), p.getLocation().getBlockZ() };

		if (args[0].equals("1"))
		{
			LogBlock_Values.areaHashMap.put("1:" + p.getUniqueId(), position);
			p.sendMessage(LogBlock_Values.POSITION1SET);
		} else if (args[0].equals("2"))
		{
			LogBlock_Values.areaHashMap.put("2:" + p.getUniqueId(), position);
			p.sendMessage(LogBlock_Values.POSITION2SET);
		} else
		{
			p.sendMessage(LogBlock_Values.WRONGCOMMANDUSE);
		}

		return true;
	}

}
