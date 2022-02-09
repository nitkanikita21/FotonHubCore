package dev.foton.hubcore.modules.interfaces.old_api.items.sub;

import dev.foton.hubcore.modules.interfaces.old_api.items.Text;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import java.util.UUID;

public class EmptyElement extends Text {
    public EmptyElement(Vector position) {
        super(Material.BLACK_STAINED_GLASS_PANE, " ", UUID.randomUUID().toString(), position, 1);
    }
}
