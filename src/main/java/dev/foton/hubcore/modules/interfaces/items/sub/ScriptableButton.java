package dev.foton.hubcore.modules.interfaces.items.sub;

import dev.foton.hubcore.modules.interfaces.items.Button;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

import java.util.List;

public abstract class ScriptableButton extends Button {

    public ScriptableButton(Material icon, String displayName, String id, List<String> description, Vector position, int count) {
        super(icon, displayName, id, description, position, count);
    }

    @Override
    public abstract void OnUse(InventoryClickEvent e);
}
