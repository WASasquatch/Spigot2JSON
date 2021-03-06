package wa.was.spigot2json.events;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import wa.was.spigot2json.util.InteractEntityJSON;

public class OnPlayerEntityInteract implements Listener {
	
    public static final int maxSize = 50;
    public static LinkedHashMap<Long, List<Object>> cache = new LinkedHashMap<Long, List<Object>>() {
    	
		private static final long serialVersionUID = 1L;
		
		@Override
        protected boolean removeEldestEntry(final Map.Entry<Long, List<Object>> eldest) {
            return size() > maxSize;
        }
		
    };
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerEntityInteract(PlayerInteractEntityEvent e) {
		if ( e.isCancelled() ) return;
		
		List<Object> list = new ArrayList<Object>();
		
		Player player = e.getPlayer();
		Entity entity = e.getRightClicked();
		Location loc = entity.getLocation();
		
		list.add(player.getUniqueId());
		list.add(player.getWorld().getName());
		list.add(loc.getBlockX() +", "+ loc.getBlockY() +", "+ loc.getBlockZ());
		list.add(entity.getType());
		list.add(entity.getName());
		list.add(entity);
		
		if ( e.getRightClicked() instanceof Player ) {
			Player livingPlayer = (Player) e.getRightClicked();
			list.add(livingPlayer);
			list.add(livingPlayer.getUniqueId());
		}
		
		cache.put(System.currentTimeMillis() / 1000L, list);
		InteractEntityJSON.updateJSON();
		
	}

}
