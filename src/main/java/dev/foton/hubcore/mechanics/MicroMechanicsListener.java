package dev.foton.hubcore.mechanics;

import dev.foton.hubcore.Main;
import dev.foton.hubcore.modules.interfaces.MenuManager;
import me.NitkaNikita.AdvancedColorAPI.api.types.builders.GradientTextBuilder;

import org.bukkit.Bukkit;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MicroMechanicsListener implements Listener {
    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        p.sendTitle(
                new GradientTextBuilder()
                        .text("&lTabLight")
                        .addColor("#fc9803")
                        .addColor("#fc0352")
                        .blur(0.4)
                        .build().getJsonText(),
                "Project",10,60,30
        );

        p.getWorld().playSound(p.getLocation(),Sound.UI_TOAST_IN, SoundCategory.RECORDS,0.5f,1f);



        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.i,() -> {
            MenuManager.open(p,MenuManager.getMenu("menu_go_to"));
        },60);
    }
}
