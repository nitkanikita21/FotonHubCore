package dev.foton.hubcore.modules.interfaces.items.sub;

import dev.foton.hubcore.modules.interfaces.items.Button;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.function.Consumer;

public class ScriptableButton extends Button {
    private Consumer<HumanEntity> script = (h)->{
        return;
    };

    public ScriptableButton(Material icon, String displayName, String id, List<String> description, Vector position, int count) {
        super(icon, displayName, id, description, position, count);
    }

    public void setScript(Consumer<HumanEntity> script) {
        this.script = script;
    }

    @Override
    public void OnUse(InventoryClickEvent e) {
        super.OnUse(e);
        script.accept(e.getWhoClicked());
    }
}
