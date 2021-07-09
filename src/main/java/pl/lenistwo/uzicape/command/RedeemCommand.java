package pl.lenistwo.uzicape.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.lenistwo.uzicape.response.RedeemResponse;
import pl.lenistwo.uzicape.service.HttpService;

import java.util.Collections;

public class RedeemCommand extends BukkitCommand {

    private final HttpService httpService;

    public RedeemCommand(@NotNull String name, String description, String usage, HttpService httpService) {
        super(name, description, usage, Collections.emptyList());
        this.httpService = httpService;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(usageMessage);
            return false;
        }

        String code = args[0];

        String message = sendRequest(code, player.getName());

        player.sendMessage(message);

        return true;
    }

    private String sendRequest(String code, String username) {
        RedeemResponse redeemResponse = httpService.redeemCode(code, username);
        return redeemResponse.getMinecraft();
    }
}
