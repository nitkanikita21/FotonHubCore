package dev.foton.hubcore.modules.interfaces.types;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class MenuRefeshItem extends MenuItem{
    public MenuRefeshItem(Material icon, String displayName, String id, List<String> description, Vector position, int count) {
        super(icon, displayName, id, description, position, count);
    }

    public MenuItem OnUpdate() {
        return this;
    }

    @Override
    public void OnUse(InventoryClickEvent e) {
        super.OnUse(e);
        if(e.getWhoClicked() instanceof Player){
            Player p = (Player) e.getWhoClicked();
            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK,1f,0.7f);
        }
    }
}
