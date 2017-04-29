package wa.was.spigot2json;

import org.bukkit.plugin.java.JavaPlugin;

// Import SHS Tags
import wa.was.webserver.lib.SHSTags;

import wa.was.spigot2json.events.OnJoinOrExit;
import wa.was.spigot2json.events.OnPlayerDeath;
import wa.was.spigot2json.events.OnPlayerEntityInteract;
import wa.was.spigot2json.events.OnPlayerInteract;
import wa.was.spigot2json.events.OnPlayerKill;
import wa.was.spigot2json.events.OnTeleport;

import wa.was.spigot2json.util.DeathsJSON;
import wa.was.spigot2json.util.InteractEntityJSON;
import wa.was.spigot2json.util.InteractJSON;
import wa.was.spigot2json.util.JoinOrExitJSON;
import wa.was.spigot2json.util.KillsJSON;
import wa.was.spigot2json.util.PlayerJSON;
import wa.was.spigot2json.util.ServerJSON;
import wa.was.spigot2json.util.TeleportJSON;

public class Spigot2JSON extends JavaPlugin {
	
    @Override
    public void onEnable() {
    	
    	// If json-server-info hasn't been entered, do so
    	if ( ! ( SHSTags.containsType("json-server-info") ) ) {  		
    		SHSTags.addReplacement("json-server-info", "{}");    		
    	}
    	
    	// If json-users-online hasn't been entered, do so
    	if ( ! ( SHSTags.containsType("json-users-online") ) ) {  		
    		SHSTags.addReplacement("json-users-online", "{}");    		
    	}
    	
    	// If json-recent-logs hasn't been entered, do so
    	if ( ! ( SHSTags.containsType("json-recent-logs") ) ) {  		
    		SHSTags.addReplacement("json-recent-logs", "{}");    		
    	}
    	
    	// If json-recent-deaths hasn't been entered, do so
    	if ( ! ( SHSTags.containsType("json-recent-deaths") ) ) {  		
    		SHSTags.addReplacement("json-recent-deaths", "{}");    		
    	}
    	
    	// If json-recent-kills hasn't been entered, do so
    	if ( ! ( SHSTags.containsType("json-recent-kills") ) ) {  		
    		SHSTags.addReplacement("json-recent-kills", "{}");    		
    	}
    	
    	// If json-recent-teleports hasn't been entered, do so
    	if ( ! ( SHSTags.containsType("json-recent-teleports") ) ) {  		
    		SHSTags.addReplacement("json-recent-teleports", "{}");    		
    	}
    	
    	// If json-recent-interactions hasn't been entered, do so
    	if ( ! ( SHSTags.containsType("json-recent-interactions") ) ) {  		
    		SHSTags.addReplacement("json-recent-interactions", "{}");    		
    	}
    	
    	// If json-recent-interactions hasn't been entered, do so
    	if ( ! ( SHSTags.containsType("json-recent-entity-interactions") ) ) {  		
    		SHSTags.addReplacement("json-recent-entity-interactions", "{}");    		
    	}
    	
    	// Register Join/Exit Listener
    	getServer().getPluginManager().registerEvents(new OnJoinOrExit(), this); 
    	
    	// Register Death Listener
    	getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this); 
    	
    	// Register Death Listener
    	getServer().getPluginManager().registerEvents(new OnPlayerKill(), this); 
    	
    	// Register Death Listener
    	getServer().getPluginManager().registerEvents(new OnTeleport(), this); 
    	
    	// Register Death Listener
    	getServer().getPluginManager().registerEvents(new OnPlayerInteract(), this); 
    	
    	// Register Death Listener
    	getServer().getPluginManager().registerEvents(new OnPlayerEntityInteract(), this); 
    	
    	// Initiate ServerJSON system
    	new ServerJSON();
    	
    	// Initiate PlayerJSON system
    	new PlayerJSON();
    	
    	// Initiate JoinOrExitJSON system
    	new JoinOrExitJSON();
    	
    	// Initiate DeathsJSON system
    	new DeathsJSON();
    	
    	// Initiate KillsJSON system
    	new KillsJSON();
    	
    	// Initiate TeleportJSON system
    	new TeleportJSON();
    	
    	// Initiate InteractJSON system
    	new InteractJSON();
    	
    	// Initiate InteractEntityJSON system
    	new InteractEntityJSON();
    	
    }

}
