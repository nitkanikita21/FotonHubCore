package dev.foton.hubcore.modules.interfaces.types;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

public class MenuRefeshItem extends MenuItem{
    public MenuRefeshItem(Material icon, String displayName, String id, Vector position, int count) {
        super(icon, displayName, id, position, count);
    }

    public MenuItem OnUpdate() {
        return this;
    }

    @Override
    public void OnUse(InventoryClickEvent e) {
        super.OnUse(e);
    }
}
