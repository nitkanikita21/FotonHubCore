package dev.foton.hubcore.modules.interfaces.menu;

import dev.foton.hubcore.modules.interfaces.types.Menu;
import org.bukkit.Bukkit;

import org.bukkit.inventory.Inventory;

import static dev.foton.hubcore.Main.format;


public class ChestMenu extends Menu {
    private int height;

    public ChestMenu(String title, String id, int height) {
        super(title,id);
        this.height = height;
    }

    @Override
    protected int getX() {
        return 9;
    }

    @Override
    protected Inventory getLocalInventory() {
        return Bukkit.createInventory(null,height*9,format(title));
    }

    public int getHeight() {
        return height;
    }
}