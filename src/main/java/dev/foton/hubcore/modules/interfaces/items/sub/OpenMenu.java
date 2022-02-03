package dev.foton.hubcore.modules.interfaces.items.sub;

import dev.foton.hubcore.modules.interfaces.MenuManager;
import dev.foton.hubcore.modules.interfaces.items.Button;
import dev.foton.hubcore.modules.interfaces.types.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

public class OpenMenu extends Button {
    private final String menuId;
    public OpenMenu(Material icon, String displayName, String id, Vector position, int count, String menu) {
        super(icon, displayName, id, position, count);
        menuId = menu;
    }
    @Override
    public void OnUse(InventoryClickEvent e) {
        super.OnUse(e);
        Menu menu = MenuManager.getMenu(menuId);
        Player player = (Player) e.getWhoClicked();
        player.openInventory(menu.getInventory());
    }
}
