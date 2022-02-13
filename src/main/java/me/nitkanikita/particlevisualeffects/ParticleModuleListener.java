package me.nitkanikita.particlevisualeffects;

import me.nitkanikita.particlevisualeffects.effectengine.effects.customeffects.TestEffect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ParticleModuleListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent p){
        PlayerEffectsStorage.setPlayerEffect(p.getPlayer(), new TestEffect());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent p){
        PlayerEffectsStorage.clearPlayerEffect(p.getPlayer());
    }
}
