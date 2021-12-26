package dev.foton.hubcore.modules.interfaces.items.sub;

import dev.foton.hubcore.modules.interfaces.items.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class ExecuteCommand extends Button {
    private String command;
    public ExecuteCommand(Material icon, String displayName, String id, List<String> description, Vector position, int count, String cmd) {
        super(icon, displayName, id, description, position, count);
        command = cmd;
    }

    @Override
    public void OnUse(InventoryClickEvent e) {
        super.OnUse(e);
        if(e.getWhoClicked() instanceof Player){
            Player p = (Player) e.getWhoClicked();
            p.chat(command);
        }
    }
}
