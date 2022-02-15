package com.nitkanikita.interfaces.menus;

import com.nitkanikita.interfaces.Point;
import com.nitkanikita.interfaces.elements.MenuItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class DispenserMenu extends Menu implements GridInventory {

    public DispenserMenu(Component title) {
        super(title);
    }

    @Override
    protected Inventory createInventory() {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.DISPENSER,  title);

        for (Map.Entry<Integer, MenuItem> entry : elements.entrySet()) {
            inventory.setItem(entry.getKey(),entry.getValue());
        }

        return inventory;
    }

    @Override
    public void setSlot(Point slot, MenuItem item) {
        int intSlot = (slot.y()*3) + slot.x();
        setSlot(intSlot, item);
    }

    @Override
    public MenuItem getSlot(Point slot) {
        int intSlot = (slot.y()*3) + slot.x();
        return getSlot(intSlot);
    }
}
