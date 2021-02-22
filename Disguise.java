package me.spaceramen.disguise;

import me.spaceramen.disguise.nickname.NickCommand;
import me.spaceramen.disguise.nickname.NickManager;
import me.spaceramen.disguise.nickname.Nicks;
import me.spaceramen.disguise.vanish.VanishManager;
import me.spaceramen.disguise.util.Log;
import me.spaceramen.disguise.vanish.VanishCommand;
import me.spaceramen.disguise.vanish.Vanished;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Disguise extends JavaPlugin 
{
    // Disguise: A Simple Secretivity Plugin for 1.12.x
    
    private static Disguise instance;
    public static Disguise getInstance()
    {
        return instance;
    }
    
    @Override
    public void onEnable()
    {
        instance = this;
        loadListeners();
        Log.info("Loaded listeners.");
        loadCommands();
        Log.info("Loaded commands.");
        loadConfig();
        Log.info("Loaded configuration.");
        Log.info("Enabled.");
    }
    
    @Override
    public void onDisable()
    {
        instance = null;
        Log.info("Disabled.");
    }
    
    private void loadListeners()
    {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new NickManager(this), this);
        manager.registerEvents(new VanishManager(this), this);
    }
    
    private void loadCommands()
    {
        this.getCommand("disguisenick").setExecutor(new NickCommand());
        this.getCommand("disguisevanish").setExecutor(new VanishCommand());
    }
    
    private void loadConfig()
    {
        getConfig().options().copyDefaults(true);
        saveConfig();
        Nicks nicks = Nicks.getConfig();
        nicks.options().copyDefaults(true);
        nicks.save();
        Vanished vanished = Vanished.getConfig();
        vanished.options().copyDefaults(true);
        vanished.save();
    }
}
