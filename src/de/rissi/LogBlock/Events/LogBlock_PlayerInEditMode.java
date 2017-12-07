package de.rissi.LogBlock.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.rissi.LogBlock.Commands.LogBlock_Gui;
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
	
	@EventHandler
    public void OnInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        
        if(e.getItem() != null && e.getItem().getType() == Material.WOOD_SPADE)
        {
        	Integer[] position = {e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ()};
        	LogBlock_Values.areaHashMap.put("1:" + p.getUniqueId(), position);
        	p.sendMessage(LogBlock_Values.POSITION1SET);
        }else if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getItem() != null && e.getItem().getType() == Material.WOOD_SPADE)
        {
        	Integer[] position = {e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ()};
        	LogBlock_Values.areaHashMap.put("2:" + p.getUniqueId(), position);
        	p.sendMessage(LogBlock_Values.POSITION2SET);
        }
       
    }
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		Player p = (Player) e.getPlayer();
		if (LogBlock_Values.inEditMode.containsKey(p.getUniqueId())
				&& LogBlock_Values.inEditMode.get(p.getUniqueId()) == true)
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		Player p = (Player) e.getPlayer();
		if (LogBlock_Values.inEditMode.containsKey(p.getUniqueId())
				&& LogBlock_Values.inEditMode.get(p.getUniqueId()) == true)
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e)
	{
		if (e.getEntity() instanceof Player)
		{
			Player p = (Player) e.getEntity();
			if (LogBlock_Values.inEditMode.containsKey(p.getUniqueId())
					&& LogBlock_Values.inEditMode.get(p.getUniqueId()) == true)
			{
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e)
	{
		Player p = (Player) e.getEntity();
		if (LogBlock_Values.inEditMode.containsKey(p.getUniqueId())
				&& LogBlock_Values.inEditMode.get(p.getUniqueId()) == true)
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void OnPlayerPickup(PlayerPickupItemEvent e)
	{
		Player p = e.getPlayer();
		if (LogBlock_Values.inEditMode.containsKey(p.getUniqueId())
				&& LogBlock_Values.inEditMode.get(p.getUniqueId()) == true)
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		if (LogBlock_Values.inEditMode.containsKey(p.getUniqueId())
				&& LogBlock_Values.inEditMode.get(p.getUniqueId()) == true)
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		if (LogBlock_Values.inEditMode.containsKey(p.getUniqueId())
				&& LogBlock_Values.inEditMode.get(p.getUniqueId()) == true)
		{
			LogBlock_Gui.leaveEditMode(p);
		}
	}
}