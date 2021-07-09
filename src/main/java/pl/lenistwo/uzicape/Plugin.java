package pl.lenistwo.uzicape;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.bukkit.plugin.java.JavaPlugin;
import pl.lenistwo.uzicape.command.RedeemCommand;
import pl.lenistwo.uzicape.service.ConfigLoader;
import pl.lenistwo.uzicape.service.HttpService;

import java.util.Objects;

public class Plugin extends JavaPlugin {

    private static final String CONFIG_FILE_NAME = "config.json";
    private static final String CAPE_COMMAND = "cape";

    private HttpService httpService;

    @Override
    public void onEnable() {
        init();
        registerCommands();
    }

    private void init() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        OkHttpClient okHttpClient = new OkHttpClient();
        ConfigLoader configLoader = new ConfigLoader(gson, getDataFolder().getPath() + "\\" + CONFIG_FILE_NAME);
        this.httpService = new HttpService(gson, configLoader.load(), okHttpClient);
    }

    private void registerCommands(){
        Objects.requireNonNull(this.getCommand(CAPE_COMMAND)).setExecutor(new RedeemCommand(CAPE_COMMAND, httpService));
    }
}
