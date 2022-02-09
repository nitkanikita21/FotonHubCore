package dev.foton.hubcore.modules.interfaces.old_api.items;

import dev.foton.hubcore.modules.interfaces.old_api.types.MenuItem;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class Text extends MenuItem {
    public Text(Material icon, String displayName, String id, Vector position, int count) {
        super(icon, displayName, id, position, count);
    }
}
