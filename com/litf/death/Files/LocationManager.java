package com.litf.death.Files;

import com.litf.death.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class LocationManager {
    private Main plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public LocationManager(Main plugin){
        this.plugin=plugin;
        saveDefaultConfig();
    }

    public void reloadConfig(){
        if(this.configFile==null)
            this.configFile = new File(this.plugin.getDataFolder(),"serverdata.yml");
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defaultStream = this.plugin.getResource("serverdata.yml");
        if(defaultStream!=null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }
    public FileConfiguration getConfig(){
        if(this.dataConfig==null)
            reloadConfig();
        return this.dataConfig;
    }
    public void saveConfig(){
        if(this.dataConfig ==null||this.configFile==null)
            return;
        try {
            this.getConfig().save(this.configFile);
        }catch(IOException ex){
            plugin.getLogger().log(Level.SEVERE, "Could not save config to "+this.configFile, ex);
        }
    }
    public void saveDefaultConfig(){
        if(this.configFile==null)
            this.configFile = new File(this.plugin.getDataFolder(), "serverdata.yml");
        if(!this.configFile.exists()){
            this.plugin.saveResource("serverdata.yml", false);
        }
    }
}
