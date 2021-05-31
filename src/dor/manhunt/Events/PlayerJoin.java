package dor.manhunt.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dor.manhunt.Main;

public class PlayerJoin implements Listener{
	
	public Main main;
	
	public PlayerJoin(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		player.setResourcePack("https://www.dropbox.com/sh/i13ss67tbf1blxl/AADiPuVLFCN51uLeArc9x5K9a?dl=1");
	}

}
