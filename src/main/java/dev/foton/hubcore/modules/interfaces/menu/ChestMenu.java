package dev.foton.hubcore.modules.interfaces.menu;

import dev.foton.hubcore.Main;
import dev.foton.hubcore.modules.interfaces.types.Menu;
import dev.foton.hubcore.modules.interfaces.types.MenuItem;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import static dev.foton.hubcore.Main.format;


public class ChestMenu extends Menu {
    private int height;

    public ChestMenu(String name, String id, int height) {
        super(name,id);
        this.height = height;
    }

    @Override
    public Inventory getInventory() {
        Inventory menu = Bukkit.createInventory(null,height*9,format(name));

        for (MenuItem el : elements.values()) {

            ItemStack item = new ItemStack(el.getIcon());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(el.getDisplayName());
            meta.setLore(el.getLore());
            item.setAmount(el.getCount());
            meta.getPersistentDataContainer().set(new NamespacedKey(Main.i,"elementId"), PersistentDataType.STRING, el.getId());
            meta.getPersistentDataContainer().set(new NamespacedKey(Main.i,"menuId"), PersistentDataType.STRING, this.getId());
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);

            double v = (el.getPosition().getY() * 9) + el.getPosition().getX();
            menu.setItem((int) v,item);
        }

        return menu;
    }

}
