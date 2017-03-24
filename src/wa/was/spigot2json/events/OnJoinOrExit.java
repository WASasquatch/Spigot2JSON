package wa.was.spigot2json.events;

import wa.was.spigot2json.util.JoinOrExitJSON;
import wa.was.spigot2json.util.PlayerJSON;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnJoinOrExit implements Listener {
	
    public static final int maxSize = 50;
    public static LinkedHashMap<Long, List<Object>> cache = new LinkedHashMap<Long, List<Object>>() {
    	
		private static final long serialVersionUID = 1L;
		
		@Override
        protected boolean removeEldestEntry(final Map.Entry<Long, List<Object>> eldest) {
            return size() > maxSize;
        }
		
    };
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		if ( Bukkit.getServer().getOnlinePlayers().size() > 0 ) {
			PlayerJSON.updateJSON();
		}
		
		List<Object> list = new ArrayList<Object>();
		
		Player player = e.getPlayer();
		String event = e.getEventName();
		String msg = e.getJoinMessage();
		boolean async = e.isAsynchronous();
		
		list.add(player.getUniqueId());
		list.add(event);
		list.add(ChatColor.stripColor(msg));
		list.add(async);
		
		cache.put(System.currentTimeMillis() / 1000L, list);
		JoinOrExitJSON.updateJSON();
		
	}
	
	@EventHandler
	public void onExit(PlayerQuitEvent e) {
		
		if ( Bukkit.getServer().getOnlinePlayers().size() > 0 ) {
			PlayerJSON.updateJSON();
		}
		
		List<Object> list = new ArrayList<Object>();
		
		Player player = e.getPlayer();
		String event = e.getEventName();
		String msg = ChatColor.stripColor(e.getQuitMessage());
		boolean async = e.isAsynchronous();
		
		list.add(player.getUniqueId());
		list.add(event);
		list.add(msg);
		list.add(async);
		
		cache.put(System.currentTimeMillis() / 1000L, list);
		JoinOrExitJSON.updateJSON();
		
	}

}
