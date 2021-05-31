package dor.manhunt.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dor.manhunt.Main;
import dor.manhunt.Utils.ItemManager;

public class WandCommand implements CommandExecutor, Listener{
	
	public Main main;
	
	public WandCommand(Main main) {
		this.main = main;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		if(!(sender instanceof Player)) {
			System.out.println("Cannot performe this action over console!");
			return false;
		}
		
		Player player = (Player) sender;
		
		openGUI(player);
		
		
		return false;
	}
	
	
	public void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "§c§lאוו... השרביט הסודית שלי!");
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("§6§lשבי השרביט");
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add("§bשבי השרביט החזק ביותר!");
		meta.setLore(lore);
		meta.setCustomModelData(3131);
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		
		meta.setDisplayName("§cתעשו סאב לאדיר!");
		lore = new ArrayList<String>();
		lore.add("§fותפעילו את הפעמון!");
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		inv.setItem(0, item);
		inv.setItem(1, item);
		inv.setItem(2, item);
		inv.setItem(3, item);
		inv.setItem(4, item);
		inv.setItem(5, item);
		inv.setItem(6, item);
		inv.setItem(7, item);
		inv.setItem(8, item);
		inv.setItem(9, item);
		inv.setItem(10, item);
		inv.setItem(11, item);
		inv.setItem(12, item);
		inv.setItem(14, item);
		inv.setItem(15, item);
		inv.setItem(16, item);
		inv.setItem(17, item);
		inv.setItem(18, item);
		inv.setItem(19, item);
		inv.setItem(20, item);
		inv.setItem(21, item);
		inv.setItem(22, item);
		inv.setItem(23, item);
		inv.setItem(24, item);
		inv.setItem(25, item);
		inv.setItem(26, item);

		player.openInventory(inv);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		if(!ChatColor.stripColor(e.getView().getTitle().toString()).equalsIgnoreCase("אוו... השרביט הסודית שלי!")) return;
		if(e.getCurrentItem() == null) return;
		if(e.getCurrentItem().getItemMeta() == null) return;
		if(e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		
		Player player = (Player) e.getWhoClicked();
		
		if(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("שבי השרביט")) {
			Inventory pInv = player.getInventory();
			player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 100);
			pInv.addItem(ItemManager.wand);
			e.setCancelled(true);
			player.sendMessage("§6§lעכשיו אני בלתי מנוצח! מוחעחעחעחע!");
			player.closeInventory();
		}
		
		if(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("תעשו סאב לאדיר!")) {
			e.setCancelled(true);
		}
	}

}
