package wa.was.spigot2json.events;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import wa.was.spigot2json.util.DeathsJSON;

public class OnPlayerDeath implements Listener {
	
    public static final int maxSize = 50;
    public static LinkedHashMap<Long, List<Object>> cache = new LinkedHashMap<Long, List<Object>>() {
    	
		private static final long serialVersionUID = 1L;
		
		@Override
        protected boolean removeEldestEntry(final Map.Entry<Long, List<Object>> eldest) {
            return size() > maxSize;
        }
		
    };
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent e) {
		
		List<Object> list = new ArrayList<Object>();
		
		Player player = e.getEntity();
		String reason = e.getDeathMessage();
		
		list.add(player.getUniqueId());
		list.add(reason);
		
		cache.put(System.currentTimeMillis() / 1000L, list);
		
		DeathsJSON.updateDeathsJSON();
		
		return;
		
	}

}
