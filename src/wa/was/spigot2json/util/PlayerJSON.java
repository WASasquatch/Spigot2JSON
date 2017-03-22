package wa.was.spigot2json.util;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import wa.was.webserver.lib.SHSTags;

public class PlayerJSON {
	
	private static JSONObject JSON;
	public static HashMap<UUID, HashMap<String, String>> playerMap 
	= new HashMap<UUID, HashMap<String, String>>();
	
	public PlayerJSON() {
		JSON = new JSONObject();
		updatePlayerJSON();
	}
	

	@SuppressWarnings("unchecked")
	public static void updatePlayerJSON() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			
			AttributeInstance playerMaxHealth  = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("name", player.getName());
			map.put("displayname", ChatColor.stripColor(player.getDisplayName()));
			map.put("gamemode", ""+player.getGameMode());
			map.put("player-op", ( player.isOp() ? "true" : "false" ));
			map.put("health", ""+player.getHealth());
			map.put("max-health", ""+playerMaxHealth.getBaseValue());
			map.put("food-level", ""+player.getFoodLevel());
			map.put("exhaustion", ""+player.getExhaustion());
			map.put("experience", ""+player.getExp());
			map.put("experience-level", ""+player.getExpToLevel());
			map.put("first-played", ""+player.getFirstPlayed());
			
			JSONObject playerJSON = new JSONObject();
			playerJSON.putAll(map);
		    JSON.put(player.getUniqueId().toString(), playerJSON);
		    
		    setSHSTag();
		    
		}
	}
	
	public static String getJSON() {
		return JSON.toJSONString();
	}
	
	public static String getString() {
		return JSON.toString();
	}
	
	public static void setSHSTag() {
		SHSTags.addReplacement("json-users-online", getJSON());
	}
	

}
