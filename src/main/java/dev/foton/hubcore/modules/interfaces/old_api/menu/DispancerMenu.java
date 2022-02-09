package dev.foton.hubcore.modules.interfaces.old_api.menu;

import dev.foton.hubcore.modules.interfaces.old_api.types.Menu;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import static dev.foton.hubcore.Main.format;

public class DispancerMenu extends Menu {

    public DispancerMenu(String name, String id) {
        super(name,id);
    }

    @Override
    protected int getX() {
        return 3;
    }

    @Override
    protected Inventory getLocalInventory() {
        return Bukkit.createInventory(null, InventoryType.DISPENSER, format(title));
    }

}
