package wa.was.spigot2json.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import wa.was.spigot2json.events.OnPlayerKill;
import wa.was.webserver.lib.SHSTags;

public class KillsJSON {
	
	private static JSONObject JSON;
	
	public KillsJSON() {
		JSON = new JSONObject();
		updateJSON();
	}
	

	@SuppressWarnings("unchecked")
	public static void updateJSON() {
		
		JSONObject playerJSON = new JSONObject();
		
		if ( OnPlayerKill.cache.size() > 0 ) {
		
			for (Map.Entry<Long, List<Object>> entry : OnPlayerKill.cache.entrySet()) {
				
				HashMap<String, Object> playerMap = new HashMap<String, Object>();
				JSONObject killJSON = new JSONObject();
				
				List<Object> list = entry.getValue();
				
				Player player = Bukkit.getServer().getPlayer((UUID)list.get(0));
				long timestamp = (long) entry.getKey();
				String event = list.get(1).toString();
					
				playerMap.put(player.getName(), event);
					
				killJSON.putAll(playerMap);
				playerJSON.put(timestamp, killJSON);
			    
			}
		
		}
		
	    JSON.put("latest-kills", playerJSON);
	    setSHSTag();
	    
	}
	
	public static String getJSON() {
		return JSON.toJSONString();
	}
	
	public static String getString() {
		return JSON.toString();
	}
	
	public static void setSHSTag() {
		SHSTags.addReplacement("json-recent-kills", getJSON());
	}

}
