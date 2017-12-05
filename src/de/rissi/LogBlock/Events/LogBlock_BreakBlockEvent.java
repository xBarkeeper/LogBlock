package de.rissi.LogBlock.Events;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.rissi.LogBlock.Main.LogBlock;
import de.rissi.LogBlock.Main.LogBlock_Values;
import de.rissi.LogBlock.Database.*;

public class LogBlock_BreakBlockEvent implements Listener
{

	@SuppressWarnings("unused")
	private LogBlock plugin;

	public LogBlock_BreakBlockEvent(LogBlock LogBlock)
	{
		this.plugin = LogBlock;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = (Player) e.getPlayer();

		String coords = e.getBlock().getX() + "," + e.getBlock().getY() + "," + e.getBlock().getZ();
		

		Databse_Utils.scribe("INSERT INTO " + LogBlock_Values.DATABASE_DBNAME + ".block "
							+ "(`Block_Typ`, `Block_Coords`, `Block_BreakTime`, `Player_UUID`)"
							+ " VALUES "
							+ "("
							+ "'" + e.getBlock().getType() + "',"
							+ "'" + coords + "',"
							+ "'" + getCurrentTimeStamp() + "',"
							+ "'" + p.getUniqueId() + "'"
							+ ")");
	}
	
	public static String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}

}