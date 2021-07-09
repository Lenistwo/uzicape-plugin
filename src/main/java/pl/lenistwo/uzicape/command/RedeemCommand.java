package pl.lenistwo.uzicape.command;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.lenistwo.uzicape.response.RedeemResponse;
import pl.lenistwo.uzicape.service.HttpService;

@RequiredArgsConstructor
public class RedeemCommand implements CommandExecutor {

    private final String commandName;
    private final HttpService httpService;

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (args.length > 1) {
            return false;
        }

        if (!command.getName().equalsIgnoreCase(commandName)){
            return false;
        }

        Player player = (Player) sender;
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
