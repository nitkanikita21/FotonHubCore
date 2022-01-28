package dev.foton.hubcore.modules.interfaces.items;

import dev.foton.hubcore.modules.interfaces.types.MenuItem;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import java.util.List;

public class Text extends MenuItem {
    public Text(Material icon, String displayName, String id, Vector position, int count) {
        super(icon, displayName, id, position, count);
    }
}
