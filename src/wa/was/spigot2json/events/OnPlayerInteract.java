package wa.was.spigot2json.events;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import wa.was.spigot2json.util.InteractJSON;

public class OnPlayerInteract implements Listener {
	
    public static final int maxSize = 50;
    public static LinkedHashMap<Long, List<Object>> cache = new LinkedHashMap<Long, List<Object>>() {
    	
		private static final long serialVersionUID = 1L;
		
		@Override
        protected boolean removeEldestEntry(final Map.Entry<Long, List<Object>> eldest) {
            return size() > maxSize;
        }
		
    };
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent e) {
		if ( e.isCancelled() ) return;
		
		List<Object> list = new ArrayList<Object>();
		
		Player player = e.getPlayer();
		Action action = e.getAction();
		Block block = e.getClickedBlock();
		Location loc = block.getLocation();
		
		list.add(player.getUniqueId());
		list.add(action);
		list.add(player.getWorld().getName());
		list.add(block.getType());
		list.add(block);
		list.add(loc.getBlockX() +", "+ loc.getBlockY() +", "+ loc.getBlockZ());
		list.add(block.isBlockIndirectlyPowered());
		list.add(block.isBlockPowered());
		
		cache.put(System.currentTimeMillis() / 1000L, list);
		InteractJSON.updateJSON();
		
	}

}
