package wa.was.spigot2json;

import org.bukkit.plugin.java.JavaPlugin;

// Import SHS Tags
import wa.was.webserver.lib.SHSTags;

import wa.was.spigot2json.events.JoinExitListeners;
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
    	
    	// Register Join/Exit Listner
    	getServer().getPluginManager().registerEvents(new JoinExitListeners(), this); 
    	
    	// Initiate PlayerJSON system
    	new PlayerJSON();
    	// Initiate ServerJSON system
    	new ServerJSON();
    	
    }

}
