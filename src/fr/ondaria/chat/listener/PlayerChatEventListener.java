package fr.ondaria.chat.listener;

import fr.ondaria.chat.OndariaChat;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChatEventListener implements Listener {

    private OndariaChat instance = OndariaChat.getInstance();

    @EventHandler
    public void onPlayerChatEvent(PlayerChatEvent e){
        Player sender = e.getPlayer();
        String message = e.getMessage();
        String fMessage = PlaceholderAPI.setPlaceholders(sender, "%luckperms_prefix%");

        e.setFormat(instance.getConfStr("chat_formatting.default").replace("{playername}", sender.getDisplayName()).replace("{message}", message).replace("{luckperms_prefix}",fMessage));

        if(sender.hasPermission(instance.getConfStr("chat_color.permission"))){
            e.setFormat(instance.getConfStr("chat_formatting.default").replace("{playername}", sender.getDisplayName()).replace("{message}", message).replace("{luckperms_prefix}",fMessage).replace("&","§"));


        }
    }

}
