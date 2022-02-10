package com.nitkanikita.interfaces.menus;

import com.nitkanikita.interfaces.elements.Button;
import com.nitkanikita.interfaces.elements.MenuItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Menu{

    private static final Map <UUID, Menu> menus = new HashMap<>();
    public static void pull(UUID pl, Menu menu){
        menus.put(pl, menu);
    }
    public static void pop(UUID pl){
        menus.remove(pl);
    }
    public static Menu get(UUID pl){
        return menus.get(pl);
    }



    protected Map<Integer, MenuItem> elements = new HashMap<>();
    protected InventoryType base;
    protected Component title;

    public Component getTitle() {
        return title;
    }

    public void setTitle(Component title) {
        this.title = title;
    }

    public Menu(InventoryType inventoryBase, Component title){
        base = inventoryBase;
        this.title = title;
    }

    public void setSlot(int slot, MenuItem item){
        elements.put(slot,item);
    }

    public MenuItem getSlot(int slot){
        return elements.get(slot);
    }

    public void openMenu(Player p){
        if(menus.containsKey(p.getUniqueId())){
            p.closeInventory();
            pop(p.getUniqueId());
        }
        pull(p.getUniqueId(),this);
        p.openInventory(createInventory());
    }

    public void slotClick(InventoryClickEvent e){
        if(elements.get(e.getSlot()) != null ){
            MenuItem menuItem = elements.get(e.getSlot());
            e.setCancelled(menuItem.isCancelable());

            if(menuItem instanceof Button b){
                b.onClick(e);
            }
        }
    }

    protected Inventory createInventory(){
        return Bukkit.createInventory(null, base, title);
    }


}
