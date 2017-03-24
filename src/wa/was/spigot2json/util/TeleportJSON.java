package wa.was.spigot2json.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import wa.was.spigot2json.events.OnTeleport;
import wa.was.webserver.lib.SHSTags;

public class TeleportJSON {
	
	private static JSONObject JSON;
	
	public TeleportJSON() {
		JSON = new JSONObject();
		updateJSON();
	}
	

	@SuppressWarnings("unchecked")
	public static void updateJSON() {
		
		JSONObject playerJSON = new JSONObject();
		
		if ( OnTeleport.cache.size() > 0 ) {
		
			for (Map.Entry<Long, List<Object>> entry : OnTeleport.cache.entrySet()) {
				
				HashMap<String, Object> playerMap = new HashMap<String, Object>();
				HashMap<String, Object> teleportMap = new HashMap<String, Object>();
				
				JSONObject teleportJSON = new JSONObject();
				JSONObject teleportInfoJSON = new JSONObject();
				
				List<Object> list = entry.getValue();
				
				Player player = Bukkit.getServer().getPlayer((UUID)list.get(0));
				long timestamp = (long) entry.getKey();
				
				teleportMap.put("username", player.getName());
				teleportMap.put("event-name", list.get(1).toString());
				teleportMap.put("from-world", list.get(2).toString());
				teleportMap.put("from-coordinates", list.get(3));
				teleportMap.put("to-world", list.get(4).toString());
				teleportMap.put("to-coordinates", list.get(5).toString());
				teleportMap.put("raw-from-location", list.get(6).toString());
				teleportMap.put("raw-to-location", list.get(7).toString());
				
				teleportInfoJSON.putAll(teleportMap);
				
				playerMap.put(player.getUniqueId().toString(), teleportInfoJSON);				
					
				teleportJSON.putAll(playerMap);
				playerJSON.put(timestamp, teleportJSON);
			    
			}
		
		}
		
	    JSON.put("latest-teleports", playerJSON);
	    setSHSTag();
	    
	}
	
	public static String getJSON() {
		return JSON.toJSONString();
	}
	
	public static String getString() {
		return JSON.toString();
	}
	
	public static void setSHSTag() {
		SHSTags.addReplacement("json-recent-teleports", getJSON());
	}

}
