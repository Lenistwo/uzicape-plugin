package pl.lenistwo.uzicape;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.plugin.java.JavaPlugin;
import pl.lenistwo.uzicape.service.ConfigLoader;

public class Plugin extends JavaPlugin {

    private static final String CONFIG_FILE_NAME = "config.json";

    private ConfigLoader configLoader;

    @Override
    public void onEnable() {
        super.onEnable();
    }

    public void init() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        this.configLoader = new ConfigLoader(gson, getDataFolder().getPath() + "/" + CONFIG_FILE_NAME);
    }
}
