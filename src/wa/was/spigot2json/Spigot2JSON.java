package wa.was.spigot2json;

import org.bukkit.plugin.java.JavaPlugin;

// Import SHS Tags
import wa.was.webserver.lib.SHSTags;

import wa.was.spigot2json.events.OnJoinOrExit;
import wa.was.spigot2json.events.OnPlayerDeath;
import wa.was.spigot2json.util.DeathsJSON;
import wa.was.spigot2json.util.PlayerJSON;
import wa.was.spigot2json.util.ServerJSON;

public class Spigot2JSON extends JavaPlugin {
	
    @Override
    public void onEnable() {
    	
    	// If json-users-online hasn't been entered, do so
    	if ( ! ( SHSTags.containsType("json-users-online") ) ) {  		
    		SHSTags.addReplacement("json-users-online", "{}");    		
    	}
    	
    	// If json-server-info hasn't been entered, do so
    	if ( ! ( SHSTags.containsType("json-server-info") ) ) {  		
    		SHSTags.addReplacement("json-server-info", "{}");    		
    	}
    	
    	// If json-recent-deaths hasn't been entered, do so
    	if ( ! ( SHSTags.containsType("json-recent-deaths") ) ) {  		
    		SHSTags.addReplacement("json-recent-deaths", "{}");    		
    	}
    	
    	// Register Join/Exit Listner
    	getServer().getPluginManager().registerEvents(new OnJoinOrExit(), this); 
    	
    	// Register Death Listener
    	getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this); 
    	
    	// Initiate ServerJSON system
    	new ServerJSON();
    	// Initiate PlayerJSON system
    	new PlayerJSON();
    	// Initiate DeathsJSON system
    	new DeathsJSON();

    	
    }

}
