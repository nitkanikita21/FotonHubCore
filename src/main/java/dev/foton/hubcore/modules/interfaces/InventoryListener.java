package dev.foton.hubcore.modules.interfaces;

import dev.foton.hubcore.modules.interfaces.menus.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {
    @EventHandler
    void click(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        Menu menu = Menu.get(p.getUniqueId());
        if(menu != null){
            menu.slotClick(e);
        }
    }

    @EventHandler
    void close(InventoryCloseEvent e){
        Player p = (Player) e.getPlayer();
        Menu menu = Menu.get(p.getUniqueId());
        if(menu != null){
            Menu.pop(p.getUniqueId());
        }
    }
}
