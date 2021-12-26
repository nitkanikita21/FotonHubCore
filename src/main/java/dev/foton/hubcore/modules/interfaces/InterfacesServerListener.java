package dev.foton.hubcore.modules.interfaces;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class InterfacesServerListener implements Listener {
    @EventHandler
    public void join(PlayerJoinEvent e){
        MenuManager.open(e.getPlayer(),MenuManager.getMenu("test_menu"));
    }
}
