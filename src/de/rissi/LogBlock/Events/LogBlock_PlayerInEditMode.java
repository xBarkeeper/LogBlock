package de.rissi.LogBlock.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import de.rissi.LogBlock.Commands.LogBlock_EditModeCommand;
import de.rissi.LogBlock.Main.LogBlock;
import de.rissi.LogBlock.Main.LogBlock_Values;

@SuppressWarnings("deprecation")
public class LogBlock_PlayerInEditMode implements Listener
{

	@SuppressWarnings("unused")
	private LogBlock plugin;

	public LogBlock_PlayerInEditMode(LogBlock LogBlock)
	{
		this.plugin = LogBlock;
	}

	//Do Action when Player interact with EditMode Items
	
	@EventHandler
	public void OnInteract(PlayerInteractEvent e) {
		Player p = (Player) e.getPlayer();
		if (LogBlock_Values.playerIsInEditMode(p))
		{
			if (e.getItem() != null)
			{
				 if(e.getItem().getType() == LogBlock_Values.SETFIRSTPOSITIONTOOL_MATERIAL) {
					 Bukkit.getServer().dispatchCommand(p, LogBlock_Values.MAINCOMMAND + " " + LogBlock_Values.SETAREACOMMAND + " 1");
				 }else if(e.getItem().getType() == LogBlock_Values.SETSECONDPOSITIONTOOL_MATERIAL) {
					 Bukkit.getServer().dispatchCommand(p, LogBlock_Values.MAINCOMMAND + " " + LogBlock_Values.SETAREACOMMAND + " 2");
				 }else if(e.getItem().getType() == LogBlock_Values.BACKTOOL_MATERIAL) {
					 Bukkit.getServer().dispatchCommand(p, LogBlock_Values.MAINCOMMAND + " " + LogBlock_Values.EDITMODECOMMAND + " 2");
				 }
			}
		}

	}

	//Kill Events if Player is in EditMode
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = (Player) e.getPlayer();
		if (LogBlock_Values.playerIsInEditMode(p))
		{
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = (Player) e.getPlayer();
		if (LogBlock_Values.playerIsInEditMode(p))
		{
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayeUseBukkit(PlayerBucketEmptyEvent e) {
		Player p = (Player) e.getPlayer();
		if (LogBlock_Values.playerIsInEditMode(p))
		{
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player)
		{
			Player p = (Player) e.getEntity();
			if (LogBlock_Values.playerIsInEditMode(p))
			{
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		Player p = (Player) e.getEntity();
		if (LogBlock_Values.playerIsInEditMode(p))
		{
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void OnPlayerPickup(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		if (LogBlock_Values.playerIsInEditMode(p))
		{
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (LogBlock_Values.playerIsInEditMode(p))
		{
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDropItem(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (LogBlock_Values.playerIsInEditMode(p))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (LogBlock_Values.playerIsInEditMode(p))
		{
			    	LogBlock_EditModeCommand.leaveEditMode(p);
		}
	}
}