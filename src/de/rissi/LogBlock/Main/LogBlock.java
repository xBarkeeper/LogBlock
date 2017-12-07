package de.rissi.LogBlock.Main;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import de.rissi.LogBlock.Database.Databse_Utils;
import de.rissi.LogBlock.Events.LogBlock_BlockEvent;
import de.rissi.LogBlock.Events.LogBlock_SetArenaEvent;

public class LogBlock extends JavaPlugin implements CommandExecutor, Listener
{

	@Override
	public void onEnable() {
		super.onEnable();
		registerCommands();
		registerEvents();
		Databse_Utils.setup(LogBlock_Values.DATABASE_CREATEDB_COMMAND, LogBlock_Values.DATABASE_CREATETABLE_COMMAND);
	}

	private void registerCommands() {
		LogBlock_OutsourcingCommands cLogBlock_OutsourcingCommands = new LogBlock_OutsourcingCommands(this);
		getCommand("LogBlock").setExecutor(cLogBlock_OutsourcingCommands);
	}

	private void registerEvents() {
		new LogBlock_BlockEvent(this);
		getServer().getPluginManager().registerEvents(new LogBlock_BlockEvent(this) , this);
		
		new LogBlock_SetArenaEvent(this);
		getServer().getPluginManager().registerEvents(new LogBlock_SetArenaEvent(this) , this);
	}

}
