package de.rissi.LogBlock.Events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import de.rissi.LogBlock.Database.Databse_Utils;
import de.rissi.LogBlock.Main.LogBlock;
import de.rissi.LogBlock.Main.LogBlock_Values;

public class LogBlock_BlockEvent implements Listener
{

	@SuppressWarnings("unused")
	private LogBlock plugin;

	public LogBlock_BlockEvent(LogBlock LogBlock)
	{
		this.plugin = LogBlock;
	}

	//Scribe into Database if a Block will be destroyed by a Player
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = (Player) e.getPlayer();
		Block block = e.getBlock();
		
		if(LogBlock_Values.playerIsInEditMode(p)) {
			scribeToDB(p, block, LogBlock_Values.BREAK);
		}
	}
	
	//Scribe into Database if a Block will be set by a Player
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = (Player) e.getPlayer();
		Block block = e.getBlock();
		
		if(!LogBlock_Values.playerIsInEditMode(p)) {
			scribeToDB(p, block, LogBlock_Values.CREATE);
		}
	}
	
	//Method that helps to Scribe the Block in Database
	
	@SuppressWarnings("deprecation")
	public static void scribeToDB(Player p, Block block, String Action) {

		String blockCoords = block.getX() + "," + block.getY() + "," + block.getZ();
		
		Databse_Utils.scribe("INSERT INTO " + LogBlock_Values.DATABASE_DBNAME + "." + LogBlock_Values.DATABASE_TABLE_BLOCK
				+ "(`" + LogBlock_Values.COLUMNNAME_BLOCK_TYP + "`, `" + LogBlock_Values.COLUMNNAME_BLOCK_COORDS + "`,"
				+ " `" + LogBlock_Values.COLUMNNAME_BLOCK_ACTION + "`,"
				+ " `" + LogBlock_Values.COLUMNNAME_BLOCK_EDITTIME + "`, `" + LogBlock_Values.COLUMNNAME_PLAYER_UUID + "`)"
				+ " VALUES "
				+ "("
				+ "'" + block.getTypeId() + ":" + block.getData() + "',"
				+ "'" + blockCoords + "',"
				+ "'" + Action + "',"
				+ "'" + LogBlock_Values.getCurrentTimeStamp() + "',"
				+ "'" + p.getUniqueId() + "'"
				+ ")");
	}
}