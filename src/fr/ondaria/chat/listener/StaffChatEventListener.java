package fr.ondaria.chat.listener;

import fr.ondaria.chat.OndariaChat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class StaffChatEventListener implements Listener {

    private OndariaChat instance = OndariaChat.getInstance();

    @EventHandler
    public void onStaffChatEvent(PlayerChatEvent e){
        Player sender = e.getPlayer();
        String message = e.getMessage();
        String prefix = instance.getConfStr("chat_staff.prefix_chat");
        if(message.startsWith(instance.getConfStr("chat_staff.prefix"))) {

            if(sender.hasPermission(instance.getConfStr("chat_staff.permission"))){

                for (Player player : instance.getServer().getOnlinePlayers()){
                    if(player.hasPermission(instance.getConfStr("chat_staff.permission"))){
                        player.sendMessage(prefix + message);
                    }
                }
                e.setCancelled(true);

            }else{
                e.setCancelled(false);
            }

        }else{
            e.setCancelled(false);
        }

    }

}
