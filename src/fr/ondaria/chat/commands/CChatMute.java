package fr.ondaria.chat.commands;

import fr.ondaria.chat.OndariaChat;
import fr.ondaria.chat.listener.PlayerChatEventListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CChatMute implements CommandExecutor {

    private OndariaChat instance = OndariaChat.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(!sender.hasPermission(instance.getConfStr("chat_mute.permission"))){return false;}

        if(PlayerChatEventListener.isMuted){sender.sendMessage(instance.getConfStr("message.chat_mute.unmute")); PlayerChatEventListener.isMuted = false; return false;}

        if(!PlayerChatEventListener.isMuted){sender.sendMessage(instance.getConfStr("message.chat_mute.mute")); PlayerChatEventListener.isMuted = true; return false;}

        return false;
    }
}
