package dor.manhunt.Utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import dor.manhunt.Main;

public class FireballShoot implements Listener {
	
	public Main main;

	
	private HashMap<Player, Entity> fireballShooter = new HashMap<>();
	Map<String, Long> cooldown = new HashMap<String, Long>();
	
	public FireballShoot(Main main) {
	
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {

			@Override
			public void run() {
				
				for (Player p : fireballShooter.keySet()) {
					Entity fireball = fireballShooter.get(p);
					if(fireball.isDead() || p.getLocation().distance(fireball.getLocation()) > 60) {
						fireball.getWorld().createExplosion(fireball.getLocation(), 10.0f);					
						fireball.remove();
						fireballShooter.remove(p);
						return;
					}
					Vector vector = p.getLocation().getDirection(); // B - A = DISTANCE
					fireball.setVelocity(vector.multiply(2)); //SPEED ADJUSMENT
				}
				
				
			}
			
		}, 0, 1);
		
	}

	@EventHandler
	public void shootFireball(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		
		
		if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {	
			
			if(cooldown.containsKey(player.getName())) {
				if(cooldown.get(player.getName()) > (System.currentTimeMillis())) {
					long timeLeft = (cooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
					player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "אתה צריך לחכות " + timeLeft + " שניות!");
					return;
				}
			}
			
			cooldown.put(player.getName(), System.currentTimeMillis() + (10 * 1000));
			
				if(e.getItem() == null) return;
				if(e.getItem().getItemMeta().getCustomModelData() == 3131) {
					Location loc = player.getEyeLocation();
					Entity fireball = loc.getWorld().spawnEntity(loc.add(loc.getDirection()), EntityType.FIREBALL);
					player.playSound(loc, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1,100);
					fireball.setVelocity(loc.getDirection().multiply(2));
					fireballShooter.put(player, fireball);
				}
			}
			
		}
	
	@EventHandler
	public void onFireballImpact(EntityExplodeEvent e) {
		Entity fireball = e.getEntity();
		
		if(fireball instanceof Fireball) {
			((Fireball) fireball).setIsIncendiary(false);
			((Fireball) fireball).setYield(0);
			e.setCancelled(true);
		}
	}
		
	}
	

