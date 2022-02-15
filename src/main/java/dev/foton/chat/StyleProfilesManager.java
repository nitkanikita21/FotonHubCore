package dev.foton.chat;

import dev.foton.chat.settings.PlayerStyleProfile;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class StyleProfilesManager {

    private StyleProfilesManager() {
        throw new IllegalStateException("Utility class");
    }

    private static final Map<Player, PlayerStyleProfile> settings = new HashMap<>();

    public static void setProfile(Player p){
        settings.put(p,new PlayerStyleProfile(p));
    }
    public static void setProfile(Player p, PlayerStyleProfile chatPlayer){
        settings.put(p,chatPlayer);
    }

    public static PlayerStyleProfile getProfile(Player p){
        return settings.get(p);
    }

    public static boolean checkRegistry(Player p){
        return settings.containsKey(p);
    }

    public static void resetOptions(Player p){
        settings.put(p,new PlayerStyleProfile(p));
    }
}
