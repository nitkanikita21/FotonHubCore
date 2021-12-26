package dev.foton.hubcore.modules.interfaces.types;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

import java.util.List;

public interface IMenuItem {
    Material getIcon();
    String getDisplayName();
    String getId();
    List<String> getDescription();
    Vector getPosition();
    int getCount();

    void OnUse(InventoryClickEvent e);
}
