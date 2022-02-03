package dev.foton.hubcore.modules.interfaces.items.sub;

import dev.foton.hubcore.modules.interfaces.types.MenuItem;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import java.util.UUID;

public class EmptyElement extends MenuItem {
    public EmptyElement(Vector position) {
        super(Material.BLACK_STAINED_GLASS_PANE, " ", UUID.randomUUID().toString(), position, 1);
    }
}
