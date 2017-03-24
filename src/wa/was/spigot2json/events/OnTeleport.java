package wa.was.spigot2json.events;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import wa.was.spigot2json.util.TeleportJSON;

public class OnTeleport implements Listener {
	
    public static final int maxSize = 50;
    public static LinkedHashMap<Long, List<Object>> cache = new LinkedHashMap<Long, List<Object>>() {
    	
		private static final long serialVersionUID = 1L;
		
		@Override
        protected boolean removeEldestEntry(final Map.Entry<Long, List<Object>> eldest) {
            return size() > maxSize;
        }
		
    };

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onTeleport(PlayerTeleportEvent e) {
		if ( e.isCancelled() ) return;
		
		List<Object> list = new ArrayList<Object>();
			
		String event;
		if ( e.getEventName() != null ) {
			event = e.getEventName();
		} else {
			event = null;
		}
		Player player = e.getPlayer();
		Location from = e.getFrom();
		Location to = e.getTo();
		
		list.add(player.getUniqueId());
		list.add(event);
		list.add(from.getWorld().getName());
		list.add(from.getX() +", "+ from.getY() +", "+ from.getZ());
		list.add(to.getWorld().getName());
		list.add(to.getX() +", "+ to.getY() +", "+ to.getZ());
		list.add(from.toString());
		list.add(to.toString());
		
		cache.put(System.currentTimeMillis() / 1000L, list);
		TeleportJSON.updateJSON();
		
	}
	
}
