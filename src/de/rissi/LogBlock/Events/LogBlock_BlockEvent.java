package de.rissi.LogBlock.Events;

import java.text.SimpleDateFormat;
import java.util.Date;

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

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = (Player) e.getPlayer();
		Block block = e.getBlock();
		
		scribeToDB(p, block, LogBlock_Values.BREAK);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = (Player) e.getPlayer();
		Block block = e.getBlock();
		
		scribeToDB(p, block, LogBlock_Values.CREATE);
	}
	
	@SuppressWarnings("deprecation")
	public static void scribeToDB(Player p, Block block, String Action) {

		String coords = block.getX() + "," + block.getY() + "," + block.getZ();
		
		Databse_Utils.scribe("INSERT INTO " + LogBlock_Values.DATABASE_DBNAME + "." + LogBlock_Values.DATABASE_TABLE_BLOCK
				+ "(`" + LogBlock_Values.COLUMNNAME_BLOCK_TYP + "`, `" + LogBlock_Values.COLUMNNAME_BLOCK_COORDS + "`,"
				+ " `" + LogBlock_Values.COLUMNNAME_BLOCK_ACTION + "`,"
				+ " `" + LogBlock_Values.COLUMNNAME_BLOCK_EDITTIME + "`, `" + LogBlock_Values.COLUMNNAME_PLAYER_UUID + "`)"
				+ " VALUES "
				+ "("
				+ "'" + block.getTypeId() + ":" + block.getData() + "',"
				+ "'" + coords + "',"
				+ "'" + Action + "',"
				+ "'" + getCurrentTimeStamp() + "',"
				+ "'" + p.getUniqueId() + "'"
				+ ")");
	}
	
	public static String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat(LogBlock_Values.TIMEFORMAT_DATABASE);
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}

}