package me.nitkanikita.particlevisualeffects;

import me.nitkanikita.particlevisualeffects.effectengine.effects.logic.PlayerEffect;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerEffectsStorage {
    private static Map<Player, PlayerEffect> players = new HashMap<>();

    public static void setPlayerEffect(Player p, PlayerEffect effect){
        if(!players.containsKey(p)){
            players.put(p,effect);
        }else{
            players.replace(p,effect);
        }
    }

    public static void clearPlayerEffect(Player p){
        players.remove(p);
    }

    public static Map<Player, PlayerEffect> getPlayers() {
        return players;
    }
}
