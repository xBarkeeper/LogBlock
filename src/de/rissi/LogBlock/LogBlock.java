package de.rissi.LogBlock;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.rissi.LogBlock.Events.LogBlock_BreakBlockEvent;;

public class LogBlock extends JavaPlugin implements CommandExecutor, Listener
{
	@Override
	public void onEnable() {
		super.onEnable();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents((Listener) this, this);
		registerCommands();
		registerEvents();
	}

	private void registerCommands() {
		LogBlock_OutsourcingCommands cLogBlock_OutsourcingCommands = new LogBlock_OutsourcingCommands(this);
		getCommand("LogBlock").setExecutor(cLogBlock_OutsourcingCommands);
	}
	
	private void registerEvents() {	
		new LogBlock_BreakBlockEvent(this);
	}

}
