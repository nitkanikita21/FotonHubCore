package dev.foton.chat;

import dev.foton.chat.settings.ChatPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ChatManager {

    static Map<Player, ChatPlayer> settings = new HashMap<>();

    public static void setChatPlayer(Player p){
        settings.put(p,new ChatPlayer(p));
    }
    public static void setChatPlayer(Player p, ChatPlayer chatPlayer){
        settings.put(p,chatPlayer);
    }

    public static ChatPlayer getChatPlayer(Player p){
        return settings.get(p);
    }

    public static boolean checkRegistry(Player p){
        return settings.containsKey(p);
    }
}
