package dev.foton.hubcore.modules.interfaces.menu;

import dev.foton.hubcore.Main;
import dev.foton.hubcore.modules.interfaces.types.IMenuItem;
import dev.foton.hubcore.modules.interfaces.types.Menu;
import dev.foton.hubcore.modules.interfaces.types.MenuItem;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class DispancerMenu extends Menu {

    public DispancerMenu(String name, String id) {
        super(name,id);
    }

    @Override
    public Inventory getInventory() {
        Inventory menu = Bukkit.createInventory(null, InventoryType.DISPENSER,Main.format(name));

        int y = 0;
        for (IMenuItem element : elements.values()) {
            MenuItem el = (MenuItem)element;

            ItemStack item = new ItemStack(el.getIcon());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(el.getDisplayName());
            meta.setLore(el.getDescription());
            item.setAmount(el.getCount());
            meta.getPersistentDataContainer().set(new NamespacedKey(Main.i,"elementId"), PersistentDataType.STRING, el.getId());
            meta.getPersistentDataContainer().set(new NamespacedKey(Main.i,"menuId"), PersistentDataType.STRING, this.getId());
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
            if(el.isEnchanted())item.addEnchantment(Enchantment.LUCK,1);

            double v = (el.getPosition().getY() * 3) + el.getPosition().getX();
            menu.setItem((int) v,item);
        }

        return menu;
    }
}
