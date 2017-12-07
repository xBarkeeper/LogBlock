package de.rissi.LogBlock.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.rissi.LogBlock.Main.LogBlock_Values;

public class LogBlock_Gui
{

	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if(LogBlock_Values.inEditMode.containsKey(p.getUniqueId()) && LogBlock_Values.inEditMode.get(p.getUniqueId()) == true) {
			LogBlock_Values.inEditMode.put(p.getUniqueId(), false);
			
			p.getInventory().setContents(LogBlock_Values.inventoryContents.get(p.getUniqueId()));
			p.getInventory().setArmorContents(LogBlock_Values.inventoryArmorContents.get(p.getUniqueId()));
			
			p.sendMessage("§3Du bist nun nicht mehr im Editor Mode!");
			
		}else {
			LogBlock_Values.inEditMode.put(p.getUniqueId(), true);
			
			LogBlock_Values.inventoryContents.put(p.getUniqueId(), p.getInventory().getContents());
			LogBlock_Values.inventoryArmorContents.put(p.getUniqueId(), p.getInventory().getArmorContents());
			
			p.getInventory().clear();
			p.getInventory().setHelmet(null);
			p.getInventory().setChestplate(null);
			p.getInventory().setLeggings(null);
			p.getInventory().setBoots(null);
			
			p.sendMessage("§3Du bist nun im Editor Mode!");
			
			LogBlock_Values.editModeitemsHashMap.put("positionsTool", createItem(Material.WOOD_SPADE, "§3Positions Tool", Enchantment.ARROW_INFINITE));
			LogBlock_Values.editModeitemsHashMap.put("back", createItem(Material.BARRIER, "§4Zurück", Enchantment.ARROW_INFINITE));
			
			p.getInventory().setItem(1, LogBlock_Values.editModeitemsHashMap.get("positionsTool"));
			p.getInventory().setItem(8, LogBlock_Values.editModeitemsHashMap.get("back"));
			
		}
		

		return true;
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
