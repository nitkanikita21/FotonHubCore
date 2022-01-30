package dev.foton.hubcore.modules.interfaces.items;

import dev.foton.hubcore.modules.interfaces.types.MenuItem;
import dev.foton.hubcore.modules.interfaces.types.MenuRefeshItem;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class Button extends MenuRefeshItem {
    public Button(Material icon, String displayName, String id, Vector position, int count) {
        super(icon, displayName, id, position, count);
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName();
    }

    @Override
    public void OnUse(InventoryClickEvent e) {
        super.OnUse(e);
    }

    @Override
    public Sound getClickSound() {
        return Sound.UI_BUTTON_CLICK;
    }
}
