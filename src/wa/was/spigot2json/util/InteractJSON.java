package wa.was.spigot2json.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import wa.was.spigot2json.events.OnPlayerInteract;
import wa.was.webserver.lib.SHSTags;

public class InteractJSON {
	
	private static JSONObject JSON;
	
	public InteractJSON() {
		JSON = new JSONObject();
		updateJSON();
	}
	

	@SuppressWarnings("unchecked")
	public static void updateJSON() {
		
		JSONObject playerJSON = new JSONObject();
		
		if ( OnPlayerInteract.cache.size() > 0 ) {
		
			for (Map.Entry<Long, List<Object>> entry : OnPlayerInteract.cache.entrySet()) {
				
				HashMap<String, Object> playerMap = new HashMap<String, Object>();
				HashMap<String, Object> interactionMap = new HashMap<String, Object>();
				
				JSONObject interactJSON = new JSONObject();
				JSONObject interactInfoJSON = new JSONObject();
				
				List<Object> list = entry.getValue();
				
				Player player = Bukkit.getServer().getPlayer((UUID)list.get(0));
				long timestamp = (long) entry.getKey();
				
				interactionMap.put("username", player.getName());
				interactionMap.put("action", list.get(1).toString());
				interactionMap.put("world", list.get(2).toString());
				interactionMap.put("location", (String) list.get(5));
				interactionMap.put("block-type", list.get(3).toString());
				interactionMap.put("raw-block", list.get(4).toString());
				interactionMap.put("block-indirectly-powered", (boolean) list.get(6));
				interactionMap.put("block-powered", (boolean) list.get(7));
				
				interactInfoJSON.putAll(interactionMap);
				
				playerMap.put(player.getUniqueId().toString(), interactInfoJSON);				
					
				interactJSON.putAll(playerMap);
				playerJSON.put(timestamp, interactJSON);
			    
			}
		
		}
		
	    JSON.put("latest-interactions", playerJSON);
	    setSHSTag();
	    
	}
	
	public static String getJSON() {
		return JSON.toJSONString();
	}
	
	public static String getString() {
		return JSON.toString();
	}
	
	public static void setSHSTag() {
		SHSTags.addReplacement("json-recent-interactions", getJSON());
	}

}
