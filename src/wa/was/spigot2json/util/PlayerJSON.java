package wa.was.spigot2json.util;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.json.simple.JSONObject;

import wa.was.webserver.lib.SHSTags;

public class PlayerJSON {
	
	private static JSONObject JSON;
	
	public PlayerJSON() {
		JSON = new JSONObject();
		updateJSON();
	}
	

	@SuppressWarnings("unchecked")
	public static void updateJSON() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			HashMap<String, Object> permMap = new HashMap<String, Object>();
			
			AttributeInstance playerMaxHealth  = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
			
			map.put("name", player.getName());
			map.put("display-name", ChatColor.stripColor(player.getDisplayName()));
			map.put("gamemode", player.getGameMode().toString());
			map.put("player-op", ( player.isOp() ? "true" : "false" ));
			map.put("health", ""+player.getHealth());
			map.put("max-health", playerMaxHealth.getBaseValue());
			map.put("food-level", player.getFoodLevel());
			map.put("exhaustion", player.getExhaustion());
			map.put("experience", player.getExp());
			map.put("experience-level", player.getExpToLevel());
			map.put("first-played", player.getFirstPlayed());
			
			JSONObject permJSON = new JSONObject();
			if ( player.getEffectivePermissions().size() > 0 ) {
				Set<PermissionAttachmentInfo> perms = player.getEffectivePermissions();
				for ( PermissionAttachmentInfo perm : perms ) {
					permMap.put(perm.getPermission().toString(), (boolean) perm.getValue());
				}
			}
			permJSON.putAll(permMap);
			map.put("permissions", permJSON);
			
			JSONObject playerJSON = new JSONObject();
			playerJSON.putAll(map);
		    JSON.put(player.getUniqueId().toString(), playerJSON);
		    
		}
		
	    setSHSTag();
	    
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
