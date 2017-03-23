package wa.was.spigot2json.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import wa.was.spigot2json.events.OnPlayerDeath;
import wa.was.webserver.lib.SHSTags;

public class DeathsJSON {
	
	private static JSONObject JSON;
	
	public DeathsJSON() {
		JSON = new JSONObject();
		updateDeathsJSON();
	}
	

	@SuppressWarnings("unchecked")
	public static void updateDeathsJSON() {
		
		JSONObject playerJSON = new JSONObject();
		
		if ( OnPlayerDeath.cache.size() > 0 ) {
		
			for (Map.Entry<Long, List<Object>> entry : OnPlayerDeath.cache.entrySet()) {
				
				HashMap<String, Object> playerMap = new HashMap<String, Object>();
				JSONObject deathJSON = new JSONObject();
				
				List<Object> list = entry.getValue();
				
				Player player = Bukkit.getServer().getPlayer((UUID)list.get(0));
				long timestamp = (long) entry.getKey();
				String reason = list.get(1).toString();
					
				playerMap.put(player.getName(), reason);
					
				deathJSON.putAll(playerMap);
				playerJSON.put(timestamp, deathJSON);
			    
			}
		
		}
		
	    JSON.put("latest-deaths", playerJSON);
	    setSHSTag();
	    
	}
	
	public static String getJSON() {
		return JSON.toJSONString();
	}
	
	public static String getString() {
		return JSON.toString();
	}
	
	public static void setSHSTag() {
		SHSTags.addReplacement("json-recent-deaths", getJSON());
	}

}
