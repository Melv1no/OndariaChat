package fr.ondaria.chat;

import fr.ondaria.chat.commands.CClearChat;
import fr.ondaria.chat.commands.CPrivateMessage;
import fr.ondaria.chat.listener.PlayerChatEventListener;
import fr.ondaria.chat.listener.StaffChatEventListener;
import fr.ondaria.chat.manager.PrivateMessageManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class OndariaChat extends JavaPlugin {

    @Override
    public void onEnable() {
        log = Logger.getLogger("Minecraft");
        log.severe("[+] ONDARIA CHAT");

        instance = this;

        saveDefaultConfig();

        if(!getConfig().getBoolean("plugin.enable")) return;

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) return;

        Bukkit.getPluginManager().registerEvents(new PlayerChatEventListener(), this);

        if(getConfig().getBoolean("chat_staff.enable")){ Bukkit.getPluginManager().registerEvents(new StaffChatEventListener(), this);}

        if(getConfig().getBoolean("chat_clear.enable")){ Bukkit.getPluginCommand("clearchat").setExecutor(new CClearChat());}

        if(getConfig().getBoolean("chat_dm.enable")){Bukkit.getPluginCommand("msg").setExecutor(new CPrivateMessage());Bukkit.getPluginCommand("r").setExecutor(new CPrivateMessage());  privateMessageManager = new PrivateMessageManager();}

        if(getConfig().getBoolean("chat_color.enable")){Bukkit.getPluginManager().registerEvents(new PlayerChatEventListener(), this);}
    }

    @Override
    public void onDisable() {

    }

    public static PrivateMessageManager getPrivateMessageManager(){return privateMessageManager;}
    private static PrivateMessageManager privateMessageManager;
    private static OndariaChat instance;
    public static OndariaChat getInstance(){return instance;}
    private Logger log;
    public String getConfStr(String confPath){return this.getConfig().getString(confPath).replace("{prefix}",getConfig().getString("plugin.prefix")).replace("&","§");}
}
