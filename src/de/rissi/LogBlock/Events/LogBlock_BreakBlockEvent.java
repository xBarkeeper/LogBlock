package de.rissi.LogBlock.Events;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.rissi.LogBlock.Main.LogBlock;
import de.rissi.LogBlock.Main.LogBlock_Values;;

public class LogBlock_BreakBlockEvent implements Listener
{

	@SuppressWarnings("unused")
	private LogBlock plugin;

	public LogBlock_BreakBlockEvent(LogBlock LogBlock)
	{
		this.plugin = LogBlock;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		System.out.println("TEST");
		Player p = (Player) e.getPlayer();
		
		p.sendMessage(e.getBlock().toString());
		
		
		
		
	}
	
}