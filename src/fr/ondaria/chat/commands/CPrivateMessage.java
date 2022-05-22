package fr.ondaria.chat.commands;

import fr.ondaria.chat.OndariaChat;
import fr.ondaria.chat.manager.PrivateMessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CPrivateMessage implements CommandExecutor {

    private OndariaChat instance = OndariaChat.getInstance();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (cmd.equals("r")) {

            Player pSender = (Player) sender;

            switch (args.length) {
                case 0:
                    displayHelp();
                    return false;
                default:
                    break;
            }

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < args.length; i++) {
                builder.append(args[i]).append(" ");
            }

            if(!PrivateMessageManager.getReplyDM().containsKey(pSender)){pSender.sendMessage(instance.getConfStr("message.chat_dm.no_player_found"));return false;}

            Player targetPlayer = PrivateMessageManager.getReplyDM().get(pSender);

            String receiver = instance.getConfStr("chat_dm.received").replace("{player}", sender.getName()).replace("{message}", builder.toString());
            String sent = instance.getConfStr("chat_dm.sent").replace("{player}", targetPlayer.getName()).replace("{message}", builder.toString());

            targetPlayer.sendMessage(receiver);
            sender.sendMessage(sent);

            OndariaChat.getPrivateMessageManager().getReplyDM().put(pSender, targetPlayer);
        }
        if (cmd.equals("msg")) {
            switch (args.length) {
                case 0:
                case 1:
                    displayHelp();
                    return false;
                default:
                    break;
            }

            if (instance.getServer().getPlayer(args[0]) == null) {
                displayHelp();
                return false;
            }
            Player targetPlayer = instance.getServer().getPlayer(args[0]);
            Player pSender = (Player) sender;

            StringBuilder builder = new StringBuilder();

            for (int i = 1; i < args.length; i++) {
                builder.append(args[i]).append(" ");
            }

            String receiver = instance.getConfStr("chat_dm.received").replace("{player}", sender.getName()).replace("{message}", builder.toString());
            String sent = instance.getConfStr("chat_dm.sent").replace("{player}", targetPlayer.getName()).replace("{message}", builder.toString());

            targetPlayer.sendMessage(receiver);
            sender.sendMessage(sent);

            OndariaChat.getPrivateMessageManager().getReplyDM().put(targetPlayer, pSender);

            return false;
        }
        return false;
    }

    private void displayHelp() {

    }
}
