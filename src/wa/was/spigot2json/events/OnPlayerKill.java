package wa.was.spigot2json.events;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import wa.was.spigot2json.util.KillsJSON;

public class OnPlayerKill implements Listener {
	
    public static final int maxSize = 50;
    public static LinkedHashMap<Long, List<Object>> cache = new LinkedHashMap<Long, List<Object>>() {
    	
		private static final long serialVersionUID = 1L;
		
		@Override
        protected boolean removeEldestEntry(final Map.Entry<Long, List<Object>> eldest) {
            return size() > maxSize;
        }
		
    };
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerKill(PlayerDeathEvent e) {
		
		Player killed = e.getEntity();
		
		if ( killed.getKiller() instanceof Player && killed instanceof Player ) {
			
			List<Object> list = new ArrayList<Object>();
			
			Player killer = killed.getKiller();
			String itemName;
			if ( killer.getInventory().getItemInMainHand() != null 
					&& ! ( killer.getInventory().getItemInMainHand().getType().name().equalsIgnoreCase("air") ) ) {
				ItemStack item = killer.getInventory().getItemInMainHand();
				if ( item.getItemMeta() == null 
						|| item.getItemMeta().getDisplayName() == null ) {
					itemName = WordUtils.capitalize(item.getType().name().replace("_", " ").toLowerCase());
				} else {
					ItemMeta itemMeta = item.getItemMeta();
					itemName = ChatColor.stripColor(itemMeta.getDisplayName());
				}
			} else {
				itemName = "bare hands";
			}
			list.add(killer.getUniqueId());
			list.add("Killed "+ killed.getName() +" with "+ itemName +".");
			
			cache.put(System.currentTimeMillis() / 1000L, list);
			KillsJSON.updateJSON();
			
		}
		
	}

}
