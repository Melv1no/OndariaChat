package fr.ondaria.chat.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PrivateMessageManager {

    private static HashMap<Player, Player> replyDM;

    public static  HashMap<Player, Player> getReplyDM(){return replyDM;}

    public PrivateMessageManager(){
        replyDM = new HashMap<Player,Player>();
    }
}
