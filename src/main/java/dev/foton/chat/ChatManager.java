package dev.foton.chat;

import dev.foton.chat.settings.PlayerStyleProfile;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ChatManager {

    static Map<Player, PlayerStyleProfile> settings = new HashMap<>();

    public static void setChatPlayer(Player p){
        settings.put(p,new PlayerStyleProfile(p));
    }
    public static void setChatPlayer(Player p, PlayerStyleProfile chatPlayer){
        settings.put(p,chatPlayer);
    }

    public static PlayerStyleProfile getChatPlayer(Player p){
        return settings.get(p);
    }

    public static boolean checkRegistry(Player p){
        return settings.containsKey(p);
    }
}
