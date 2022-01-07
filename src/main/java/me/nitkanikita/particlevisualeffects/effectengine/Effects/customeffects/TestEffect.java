package me.nitkanikita.particlevisualeffects.effectengine.Effects.customeffects;

import me.nitkanikita.particlevisualeffects.effectengine.Effects.logic.PlayerEffectBasic;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TestEffect extends PlayerEffectBasic {


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
