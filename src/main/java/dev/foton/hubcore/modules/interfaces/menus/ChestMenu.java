package dev.foton.hubcore.modules.interfaces.menus;

import dev.foton.hubcore.modules.interfaces.elements.MenuItem;
import dev.foton.hubcore.modules.interfaces.Point;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class ChestMenu extends Menu implements ChestableMenu {
    private int height;

    public ChestMenu(int height, Component title) {
        super(InventoryType.CHEST,title);
        this.height = height;
    }

    @Override
    protected Inventory createInventory() {
        Inventory inventory = Bukkit.createInventory(null, height*9, title);

        for (Map.Entry<Integer, MenuItem> entry : elements.entrySet()) {
            inventory.setItem(entry.getKey(),entry.getValue());
        }

        return inventory;
    }

    @Override
    public void setSlot(Point slot, MenuItem item) {
        int intSlot = (slot.y()*9) + slot.x();
        setSlot(intSlot, item);
    }

    @Override
    public MenuItem getSlot(Point slot) {
        int intSlot = (slot.y()*9) + slot.x();
        return getSlot(intSlot);
    }
}
