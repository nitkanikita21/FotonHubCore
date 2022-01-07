package me.nitkanikita.particlevisualeffects.effectengine;

import me.nitkanikita.particlevisualeffects.PlayerEffectsStorage;
import org.bukkit.entity.Player;

public class RenderEffectRunnable implements Runnable{
    @Override
    public void run() {
        for (Player player : PlayerEffectsStorage.getPlayers().keySet()) {
            PlayerEffectsStorage.getPlayers().get(player).renderEffect(player);
        }
    }
}
