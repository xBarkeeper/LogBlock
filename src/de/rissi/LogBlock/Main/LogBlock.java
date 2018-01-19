package de.rissi.LogBlock.Main;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import de.rissi.LogBlock.Database.Databse_Utils;
import de.rissi.LogBlock.Events.LogBlock_BlockEvent;
import de.rissi.LogBlock.Events.LogBlock_PlayerInEditMode;
import de.rissi.LogBlock.Events.LogBlock_SetPositionWithTool;

public class LogBlock extends JavaPlugin implements CommandExecutor, Listener
{
	
	//Enable Plugin and Setup Database
	
	@Override
	public void onEnable() {
		super.onEnable();
		registerCommands();
		registerEvents();
		Databse_Utils.setup(LogBlock_Values.DATABASE_CREATEDB_COMMAND, LogBlock_Values.DATABASE_CREATETABLE_COMMAND);
	}
	
	//Register Command

	private void registerCommands() {
		LogBlock_OutsourcingCommands cLogBlock_OutsourcingCommands = new LogBlock_OutsourcingCommands(this);
		getCommand(LogBlock_Values.MAINCOMMAND).setExecutor(cLogBlock_OutsourcingCommands);
	}
	
	//Register Event Classes 

	private void registerEvents() {
		new LogBlock_BlockEvent(this);
		getServer().getPluginManager().registerEvents(new LogBlock_BlockEvent(this) , this);
		
		new LogBlock_SetPositionWithTool(this);
		getServer().getPluginManager().registerEvents(new LogBlock_SetPositionWithTool(this) , this);
		
		new LogBlock_PlayerInEditMode(this);
		getServer().getPluginManager().registerEvents(new LogBlock_PlayerInEditMode(this) , this);
	}

}
