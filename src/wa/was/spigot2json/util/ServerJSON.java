package wa.was.spigot2json.util;

import java.util.HashMap;

import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.json.simple.JSONObject;

import wa.was.webserver.lib.SHSTags;

public class ServerJSON {
	
	private static JSONObject JSON;
	
	public ServerJSON() {
		JSON = new JSONObject();
		updateJSON();
	}	

	@SuppressWarnings("unchecked")
	public static void updateJSON() {
		
		Server server = Bukkit.getServer();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", server.getName());
		map.put("display-name", server.getServerName());
		map.put("bukkit-version", server.getBukkitVersion());
		map.put("ip", server.getIp());
		map.put("port", server.getPort());
		map.put("version", server.getVersion());
		map.put("shutdown-message", ChatColor.stripColor(server.getShutdownMessage()));
		map.put("motd", ChatColor.stripColor(server.getMotd()));
		map.put("world-type", server.getWorldType());
		map.put("allow-nether", server.getAllowNether());
		map.put("allow-end", server.getAllowEnd());
		map.put("allow-flight", server.getAllowFlight());
		map.put("ambient-spawn-limit", server.getAmbientSpawnLimit());
		map.put("animal-spawn-limit", server.getAnimalSpawnLimit());
		map.put("monster-spawn-limit", server.getMonsterSpawnLimit());
		map.put("connection-throttle", server.getConnectionThrottle());
		map.put("default-gamemode", server.getDefaultGameMode().toString());
		map.put("generate-structures", server.getGenerateStructures());
		map.put("idle-timeout", server.getIdleTimeout());
		map.put("max-players", server.getMaxPlayers());
		map.put("players-online-count", server.getOnlinePlayers().size());
		map.put("spawn-radius", server.getSpawnRadius());
		map.put("water-animal-spawn-limit", server.getWaterAnimalSpawnLimit());
		map.put("ticks-per-animal-spawns", server.getTicksPerAnimalSpawns());
		map.put("ticks-per-monster-spawns", server.getTicksPerMonsterSpawns());
		map.put("view-distance", server.getViewDistance());
		map.put("whitelisted", server.hasWhitelist());
		map.put("hardcore-mode", server.isHardcore());
		map.put("primary-thread", server.isPrimaryThread());
		
		int cur = 0;
		HashMap<Integer, String> ipBans = new HashMap<Integer, String>();
		
		for(String ban : server.getIPBans()) {
			ipBans.put(cur, ban);
			cur++;
		}
		JSONObject ipBanJSON = new JSONObject();
		ipBanJSON.putAll(ipBans);
		map.put("ip-bans", ipBanJSON);
		
		HashMap<String, Object> bans = new HashMap<String, Object>();
		
		for ( BanEntry ban : Bukkit.getServer().getBanList(BanList.Type.NAME).getBanEntries() ) {
			JSONObject banEntry = new JSONObject();
			HashMap<String, Object> banMap = new HashMap<String, Object>();
			banMap.put("ban-created", ban.getCreated().toString());
			banMap.put("ban-source", ChatColor.stripColor(ban.getSource().toString()));
			banMap.put("ban-reason", ban.getReason().toString());
			banMap.put("ban-expiration", ban.getExpiration());
			banEntry.putAll(banMap);
			bans.put(ChatColor.stripColor(ban.getTarget()), banEntry);
		}
		
		JSONObject banJSON = new JSONObject();
		banJSON.putAll(bans);
		map.put("player-bans", banJSON);
		
		cur = 0;
		HashMap<Integer, Object> worlds = new HashMap<Integer, Object>();
		
		for (World world : server.getWorlds() ) {
			worlds.put(cur, world.getName());
			cur++;
		}
		
		JSONObject activeWorlds = new JSONObject();
		activeWorlds.putAll(worlds);		
		map.put("active-worlds", activeWorlds);
		
	    JSON.put(server.getServerId(), map);
	    
	    setSHSTag();
	    
	}
	
	public static String getJSON() {
		return JSON.toJSONString();
	}
	
	public static String getString() {
		return JSON.toString();
	}
	
	public static void setSHSTag() {
		SHSTags.addReplacement("json-server-info", getJSON());
	}
	

}
