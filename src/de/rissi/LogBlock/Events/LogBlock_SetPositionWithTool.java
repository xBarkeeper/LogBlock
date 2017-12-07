package de.rissi.LogBlock.Events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import de.rissi.LogBlock.Main.LogBlock;
import de.rissi.LogBlock.Main.LogBlock_Values;

public class LogBlock_SetPositionWithTool implements Listener
{

	@SuppressWarnings("unused")
	private LogBlock plugin;

	public LogBlock_SetPositionWithTool(LogBlock LogBlock)
	{
		this.plugin = LogBlock;
	}

	@EventHandler
    public void OnInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        
        if(e.getAction() == Action.LEFT_CLICK_BLOCK && e.getItem() != null && e.getItem().getType() == Material.WOOD_SPADE)
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
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = (Player) e.getPlayer();

		if(p.getItemInHand().getType() == Material.WOOD_SPADE && p.getGameMode() == GameMode.CREATIVE) {
			e.setCancelled(true);
		}
	}

}