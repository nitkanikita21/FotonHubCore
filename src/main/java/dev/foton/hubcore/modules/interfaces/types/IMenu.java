package dev.foton.hubcore.modules.interfaces.types;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import java.util.List;

public interface IMenu {
    String getName();
    String getId();
    void addElement(IMenuItem el);
    IMenuItem getElement(String id);

    Inventory getInventory();

}
