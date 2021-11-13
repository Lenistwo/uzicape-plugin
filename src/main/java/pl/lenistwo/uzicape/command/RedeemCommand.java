package pl.lenistwo.uzicape.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.lenistwo.uzicape.exception.ApiException;
import pl.lenistwo.uzicape.service.RestService;

import java.util.Collections;

public class RedeemCommand extends BukkitCommand {

    private final RestService restService;

    public RedeemCommand(@NotNull String name, String description, String usage, RestService restService) {
        super(name, description, usage, Collections.emptyList());
        this.restService = restService;
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

        String message = redeemCode(code, player.getName());

        player.sendMessage(message);

        return true;
    }

    private String redeemCode(String code, String username) {
        try {
            return restService.redeemCode(code, username).getMinecraft();
        } catch (ApiException e) {
            return e.getMessage();
        }
    }
}
