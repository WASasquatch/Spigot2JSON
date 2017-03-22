package wa.was.spigot2json;

import org.bukkit.plugin.java.JavaPlugin;

// Import SHS Tags
import wa.was.webserver.lib.SHSTags;

import wa.was.spigot2json.events.JoinExitListeners;
import wa.was.spigot2json.util.PlayerJSON;

public class Spigot2JSON extends JavaPlugin {
	
    @Override
    public void onEnable() {
    	
    	// If json_users_online hasn't been entered, do so
    	if ( ! ( SHSTags.containsType("json-users-online") ) ) {  		
    		SHSTags.addReplacement("json-users-online", "{}");    		
    	}
    	
    	// Register Join/Exit Listner
    	getServer().getPluginManager().registerEvents(new JoinExitListeners(), this); 
    	
    	// Initiate PlayerJSON system
    	new PlayerJSON();
    	
    }

}
