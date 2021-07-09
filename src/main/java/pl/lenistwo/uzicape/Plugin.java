package pl.lenistwo.uzicape;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.bukkit.plugin.java.JavaPlugin;
import pl.lenistwo.uzicape.command.RedeemCommand;
import pl.lenistwo.uzicape.config.Config;
import pl.lenistwo.uzicape.service.ConfigLoader;
import pl.lenistwo.uzicape.service.HttpService;

public class Plugin extends JavaPlugin {

    private static final String CONFIG_FILE_NAME = "config.json";

    private HttpService httpService;
    private Config config;

    @Override
    public void onEnable() {
        init();
        registerCommands();
    }

    private void init() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        OkHttpClient okHttpClient = new OkHttpClient();
        ConfigLoader configLoader = new ConfigLoader(gson, getDataFolder().getPath() + "\\" + CONFIG_FILE_NAME);
        this.config = configLoader.load();
        this.httpService = new HttpService(gson, config, okHttpClient);
    }

    private void registerCommands(){
        RedeemCommand redeemCommand = new RedeemCommand(config.getCommandName(), config.getCommandDescription(), config.getCommandUsage(), httpService);
        this.getServer().getCommandMap().register(config.getCommandName(), redeemCommand);
    }
}
