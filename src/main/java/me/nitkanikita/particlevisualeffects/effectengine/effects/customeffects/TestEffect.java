package me.nitkanikita.particlevisualeffects.effectengine.effects.customeffects;

import me.nitkanikita.particlevisualeffects.effectengine.effects.logic.PlayerEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TestEffect implements PlayerEffect {


    @Override
    public void renderEffect(Player player) {
        player.getWorld().spawnParticle(
                Particle.COMPOSTER,
                player.getEyeLocation().add(new Vector(0,0.4,0)),
                2,
                0.1,0.05,0.1,
                0.001
        );
    }
}
