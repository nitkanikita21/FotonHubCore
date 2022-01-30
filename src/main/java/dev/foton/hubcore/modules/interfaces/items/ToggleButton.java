package dev.foton.hubcore.modules.interfaces.items;

import dev.foton.hubcore.modules.interfaces.types.MenuItem;
import dev.foton.hubcore.modules.interfaces.types.MenuRefeshItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class ToggleButton extends MenuRefeshItem {

    protected boolean isActive = false;

    public ToggleButton(String displayName, String id, Vector position, int count) {
        super(Material.RED_WOOL, displayName, id, position, count);
    }

    @Override
    public void OnUse(InventoryClickEvent e) {
        super.OnUse(e);
        icon = icon == Material.RED_WOOL ? Material.LIME_WOOL : Material.RED_WOOL;
        isActive = !isActive;
    }

    @Override
    public Sound getClickSound() {
        return Sound.UI_BUTTON_CLICK;
    }

    @Override
    public String getDisplayName() {
        return ""+(isActive ? ChatColor.GREEN+"☑" : ChatColor.RED+"☒") + ChatColor.GRAY + " | "+super.getDisplayName();
    }

    @Override
    public MenuItem OnUpdate() {
        return this;
    }
}
