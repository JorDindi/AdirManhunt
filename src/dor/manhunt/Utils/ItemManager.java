package dor.manhunt.Utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
	
	public static ItemStack wand;
	
	public static void createWand() {
		
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta wandMeta = item.getItemMeta();
		
		wandMeta.setDisplayName("§6§lשבי השרביט");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§bהשבריט יורה כדורי אש שאפשר לכוון לאן הם הולכים!");
		wandMeta.setLore(lore);
		wandMeta.setCustomModelData(3131);
		item.setItemMeta(wandMeta);
		
		
		wand = item;
		
	}
	

}
