package fr.ondaria.chat.commands;

import fr.ondaria.chat.OndariaChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CClearChat implements CommandExecutor {

    private OndariaChat instance = OndariaChat.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!sender.hasPermission(instance.getConfStr("chat_clear.permission"))){ return false;}

        for(int i = 0; i < 350; i++){instance.getServer().broadcastMessage("");}

        return false;
    }
}
