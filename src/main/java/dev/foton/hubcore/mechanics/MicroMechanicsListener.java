package dev.foton.hubcore.mechanics;

import dev.foton.chat.StyleProfilesManager;
import dev.foton.chat.settings.PlayerStyleProfile;
import dev.foton.hubcore.Main;
import dev.foton.hubcore.MenuPrefabs;
import me.NitkaNikita.AdvancedColorAPI.api.types.builders.GradientTextBuilder;

import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class MicroMechanicsListener implements Listener {


    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();


        if(!Bukkit.getOfflinePlayer(p.getUniqueId()).hasPlayedBefore()){
            p.sendTitle(
                    new GradientTextBuilder()
                            .text("&lTabLight")
                            .addColor("#fc9803")
                            .addColor("#fc0352")
                            .blur(0.4)
                            .build().getJsonText(),
                    "Project",10,60,30
            );

            p.getWorld().playSound(p.getLocation(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.RECORDS,0.5f,1f);



            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.i,() -> {
                MenuPrefabs.GENERAL_MENU(p).openMenu(p);
                p.sendMessage(ChatColor.GRAY + "Что бы снова открыть данное меню, введи " + ChatColor.GOLD + " /menu");
            },60);
        }else {
            p.sendTitle(
                    new GradientTextBuilder()
                            .text("&lTabLight")
                            .addColor("#fc9803")
                            .addColor("#fc0352")
                            .blur(0.4)
                            .build().getJsonText(),
                    "Project",5,60,15
            );

            p.getWorld().playSound(p.getLocation(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.RECORDS,0.5f,1f);
        }

        regChatSettings(p);

        p.teleport(p.getWorld().getSpawnLocation());
    }

    public void regChatSettings(Player p){
        if(!StyleProfilesManager.checkRegistry(p)){
            StyleProfilesManager.setProfile(p);
        }
    }
}
