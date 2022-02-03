package dev.foton.hubcore.modules.interfaces.types;

import dev.foton.hubcore.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;


import java.util.HashMap;

import java.util.Map;

public abstract class Menu{
    //TODO: добавить метод для заполнения меню EmtyElement ами
    protected String title;
    protected String id;
    protected Map<String,MenuItem> elements = new HashMap<>();

    public Menu(String title, String id){
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }


    public String getId() {
        return id;
    }

    public void clearElements(){
        elements.clear();
    }


    public void addElement(MenuItem el) {
        if(!elements.containsKey(el.getId())){
            elements.put(el.getId(),el);
        }
    }


    public MenuItem getElement(String id) {
        return elements.get(id);
    }


    public final Inventory getInventory() {
        Inventory inv = getLocalInventory();
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
            if(el.isEnchanted())item.addEnchantment(Enchantment.LUCK,1);

            double v = (el.getPosition().getY() * getX()) + el.getPosition().getX();
            inv.setItem((int) v,item);
        }
        return inv;
    }

    protected abstract int getX();

    protected abstract Inventory getLocalInventory();

}
