package wa.was.spigot2json.events;

import wa.was.spigot2json.util.PlayerJSON;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import wa.was.webserver.lib.SHSTags;

public class OnJoinOrExit implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if ( Bukkit.getServer().getOnlinePlayers().size() > 0 ) {
			// Build and update json array
			PlayerJSON.updatePlayerJSON();
		} else {
			// Set json array to empty
			SHSTags.addReplacement("json-users-online", "{}");
		}
	}
	
	@EventHandler
	public void onExit(PlayerQuitEvent e) {
		if ( Bukkit.getServer().getOnlinePlayers().size() > 0 ) {
			// Build and update json array
			PlayerJSON.updatePlayerJSON();
		} else {
			// Set json array to empty
			SHSTags.addReplacement("json-users-online", "{}");
		}
	}

}
