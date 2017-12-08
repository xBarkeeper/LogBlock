package de.rissi.LogBlock.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.rissi.LogBlock.Main.LogBlock_Values;

public class LogBlock_EditModeCommand
{

	@SuppressWarnings("deprecation")
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if(LogBlock_Values.inEditMode.containsKey(p.getUniqueId()) && LogBlock_Values.inEditMode.get(p.getUniqueId()) == true) {
			
			leaveEditMode(p);
			p.updateInventory();
			
		}else {
			LogBlock_Values.inEditMode.put(p.getUniqueId(), true);
			
			inventorySetup(p);
			
			LogBlock_Values.health.put(p.getUniqueId(), p.getHealth());
			LogBlock_Values.food.put(p.getUniqueId(), p.getFoodLevel());
			LogBlock_Values.location.put(p.getUniqueId(), p.getLocation());
			
			p.setHealth(20.0);
			p.setFoodLevel(20);
			p.setAllowFlight(true);
			
			p.updateInventory();
			p.sendMessage(LogBlock_Values.ENTEREDITORMODE);
		}
		

		return true;
	}
	
	public static void leaveEditMode(Player p) {
		LogBlock_Values.inEditMode.put(p.getUniqueId(), false);
		
		p.getInventory().setContents(LogBlock_Values.inventoryContents.get(p.getUniqueId()));
		p.getInventory().setArmorContents(LogBlock_Values.inventoryArmorContents.get(p.getUniqueId()));
		p.setHealth(LogBlock_Values.health.get(p.getUniqueId()));
		p.setFoodLevel(LogBlock_Values.food.get(p.getUniqueId()));
		p.teleport(LogBlock_Values.location.get(p.getUniqueId()));
		p.setAllowFlight(false);
		
		p.sendMessage(LogBlock_Values.LEAVEEDITOTMODE);
	}
	
	public static void inventorySetup(Player p) {
		
		LogBlock_Values.inventoryContents.put(p.getUniqueId(), p.getInventory().getContents());
		LogBlock_Values.inventoryArmorContents.put(p.getUniqueId(), p.getInventory().getArmorContents());
		
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		
		p.getInventory().setItem(0, createItem(LogBlock_Values.POSITIONTOOL_MATERIAL, "§3Positions Tool", Enchantment.ARROW_INFINITE));
		p.getInventory().setItem(2, createItem(LogBlock_Values.SETFIRSTPOSITIONTOOL_MATERIAL, "§2Setz Position 1", Enchantment.ARROW_INFINITE));
		p.getInventory().setItem(3, createItem(LogBlock_Values.SETSECONDPOSITIONTOOL_MATERIAL, "§2Setz Position 2", Enchantment.ARROW_INFINITE));
		p.getInventory().setItem(8, createItem(LogBlock_Values.BACKTOOL_MATERIAL, "§4Zurück", Enchantment.ARROW_INFINITE));
	}
	
	public static ItemStack createItem(Material material, String Name, Enchantment enchantment) {
		ItemStack is = new ItemStack(material);
		ItemMeta im = is.getItemMeta();
		
		im.setDisplayName(Name);
		im.addEnchant(enchantment, 1, true);
		is.setItemMeta(im);
		
		return is;
	}

}
