package wa.was.spigot2json.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import wa.was.spigot2json.events.OnJoinOrExit;
import wa.was.webserver.lib.SHSTags;

public class JoinOrExitJSON {
	
	private static JSONObject JSON;
	
	public JoinOrExitJSON() {
		JSON = new JSONObject();
		updateJSON();
	}
	

	@SuppressWarnings("unchecked")
	public static void updateJSON() {
		
		JSONObject playerJSON = new JSONObject();
		
		if ( OnJoinOrExit.cache.size() > 0 ) {
		
			for (Map.Entry<Long, List<Object>> entry : OnJoinOrExit.cache.entrySet()) {
				
				HashMap<String, Object> playerMap = new HashMap<String, Object>();
				HashMap<String, Object> logsMap = new HashMap<String, Object>();
				
				JSONObject logsJSON = new JSONObject();
				JSONObject logsInfoJSON = new JSONObject();
				
				List<Object> list = entry.getValue();
				
				Player player = Bukkit.getServer().getPlayer((UUID)list.get(0));
				long timestamp = (long) entry.getKey();
			
				logsMap.put("username", player.getName());
				logsMap.put("display-name", ChatColor.stripColor(player.getDisplayName()));
				logsMap.put("event-name", list.get(1).toString());
				logsMap.put("message", list.get(2).toString());
				logsMap.put("asynchronous", (boolean) list.get(3));
					
				logsInfoJSON.putAll(logsMap);
				playerMap.put(player.getUniqueId().toString(), logsInfoJSON);
					
				logsJSON.putAll(playerMap);
				playerJSON.put(timestamp, logsJSON);
			    
			}
		
		}
		
	    JSON.put("latest-joins-and-exits", playerJSON);
	    setSHSTag();
	    
	}
	
	public static String getJSON() {
		return JSON.toJSONString();
	}
	
	public static String getString() {
		return JSON.toString();
	}
	
	public static void setSHSTag() {
		SHSTags.addReplacement("json-recent-logs", getJSON());
	}

}
