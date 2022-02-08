package dev.foton.hubcore.modules.interfaces.menus.old;

import dev.foton.hubcore.Main;
import dev.foton.hubcore.modules.interfaces.items.Button;
import dev.foton.hubcore.modules.interfaces.menus.old.types.Menu;
import dev.foton.hubcore.modules.interfaces.menus.old.types.MenuItem;
import dev.foton.hubcore.modules.interfaces.menus.old.types.MenuRefeshItem;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class MenuListener implements Listener {
    @EventHandler
    public void onClickItem(InventoryClickEvent e){
        if(!(e.getWhoClicked() instanceof Player))return;

        Player player = (Player) e.getWhoClicked();

        ItemStack item = e.getCurrentItem();
        if(item == null)return;
        ItemMeta meta = item.getItemMeta();
        if(meta != null){

            String elementId = (String) meta.getPersistentDataContainer().get(new NamespacedKey(Main.i,"elementId"), PersistentDataType.STRING);
            String menuId = (String) meta.getPersistentDataContainer().get(new NamespacedKey(Main.i,"menuId"), PersistentDataType.STRING);

            if(elementId != null){
                Menu menu = MenuManager.getMenu(menuId);
                MenuItem element = (MenuItem) menu.getElement(elementId);
                if(element instanceof Button){
                    Button btn = (Button) element;
                    btn.OnUse(e);
                }else if (element instanceof MenuRefeshItem){
                    MenuRefeshItem tg = (MenuRefeshItem) element;
                    tg.OnUse(e);
                    tg.OnUpdate();
                    meta.setLore(tg.getLore());
                    meta.setDisplayName(tg.getDisplayName());
                    item.setItemMeta(meta);
                    item.setType(tg.getIcon());
                    //player.closeInventory();
                    //player.openInventory(menu.getInventory());
                }


                e.setCancelled(true);
            }

        }


    }
}
