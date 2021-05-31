package dor.manhunt;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dor.manhunt.Commands.WandCommand;
import dor.manhunt.Events.PlayerJoin;
import dor.manhunt.Utils.FireballShoot;
import dor.manhunt.Utils.ItemManager;


public class Main extends JavaPlugin implements Listener{
	
	private PluginManager pm;

	public void onEnable() {
		ItemManager.createWand();
		this.pm = getServer().getPluginManager();
		registerEvents();
		registerCommands();
	}
	
	private void registerEvents() {
		this.pm.registerEvents(new FireballShoot(this), this);
		this.pm.registerEvents(new WandCommand(this), this);
		this.pm.registerEvents(new PlayerJoin(this), this);
	}
	
	public void registerCommands() {
		getCommand("gun").setExecutor(new WandCommand(this));
	}

	

}
