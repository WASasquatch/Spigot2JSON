package wa.was.spigot2json.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import wa.was.spigot2json.events.OnPlayerEntityInteract;
import wa.was.webserver.lib.SHSTags;

public class InteractEntityJSON {
	
	private static JSONObject JSON;
	
	public InteractEntityJSON() {
		JSON = new JSONObject();
		updateJSON();
	}
	

	@SuppressWarnings("unchecked")
	public static void updateJSON() {
		
		JSONObject playerJSON = new JSONObject();
		
		if ( OnPlayerEntityInteract.cache.size() > 0 ) {
		
			for (Map.Entry<Long, List<Object>> entry : OnPlayerEntityInteract.cache.entrySet()) {
				
				HashMap<String, Object> playerMap = new HashMap<String, Object>();
				HashMap<String, Object> interactionMap = new HashMap<String, Object>();
				
				JSONObject interactJSON = new JSONObject();
				JSONObject interactInfoJSON = new JSONObject();
				
				List<Object> list = entry.getValue();
				
				Player player = Bukkit.getServer().getPlayer((UUID)list.get(0));
				long timestamp = (long) entry.getKey();
				
				interactionMap.put("username", player.getName());
				interactionMap.put("world", list.get(1).toString());
				interactionMap.put("entity-location", (String) list.get(2));
				interactionMap.put("entity-type", list.get(3).toString());
				interactionMap.put("entity-name", list.get(4).toString());
				interactionMap.put("raw-entity", list.get(5).toString());
				if ( list.size() > 6 ) {
					interactionMap.put("entity-as-raw-player", list.get(6).toString());
					interactionMap.put("entity-player-guid", list.get(7).toString());
				}
				
				interactInfoJSON.putAll(interactionMap);
				
				playerMap.put(player.getUniqueId().toString(), interactInfoJSON);				
					
				interactJSON.putAll(playerMap);
				playerJSON.put(timestamp, interactJSON);
			    
			}
		
		}
		
	    JSON.put("latest-entity-interactions", playerJSON);
	    setSHSTag();
	    
	}
	
	public static String getJSON() {
		return JSON.toJSONString();
	}
	
	public static String getString() {
		return JSON.toString();
	}
	
	public static void setSHSTag() {
		SHSTags.addReplacement("json-recent-entity-interactions", getJSON());
	}

}
