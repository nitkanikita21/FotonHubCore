package me.nitkanikita.particlevisualeffects;

import me.nitkanikita.particlevisualeffects.effectengine.Effects.logic.PlayerEffectBasic;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerEffectsStorage {
    private static Map<Player, PlayerEffectBasic> players = new HashMap<>();

    public static void setPlayerEffect(Player p, PlayerEffectBasic effect){
        if(!players.containsKey(p)){
            players.put(p,effect);
        }else{
            players.replace(p,effect);
        }
    }

    public static void clearPlayerEffect(Player p){
        players.remove(p);
    }

    public static Map<Player, PlayerEffectBasic> getPlayers() {
        return players;
    }
}
