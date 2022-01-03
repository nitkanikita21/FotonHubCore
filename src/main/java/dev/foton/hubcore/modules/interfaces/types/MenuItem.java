package dev.foton.hubcore.modules.interfaces.types;

import dev.foton.hubcore.Main;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class MenuItem{
    protected Material icon;
    protected String displayName;
    protected String id;
    protected List<String> description;
    protected Vector position;
    protected int count;
    protected boolean enchanted = false;

    public MenuItem(
            Material icon, String displayName,
            String id, List<String> description,
            Vector position,
            int count
    ){
        this.icon = icon;
        this.displayName = displayName;
        this.id = id;
        this.description = description;
        this.position = position;
        this.count = count;

        position.setX(position.getX()-1);
        position.setY(position.getY()-1);
    }

    public void setEnchanted(boolean enchanted) {
        this.enchanted = enchanted;
    }

    public boolean isEnchanted() {
        return enchanted;
    }

    public void addDescriptionLine(String s){
        description.add(Main.format(s));
    }
    public void removeDescriptionLine(int i){
        description.remove(i);
    }

    public Material getIcon() {
        return icon;
    }

    public String getDisplayName() {
        return Main.format(displayName);
    }


    public String getId() {
        return id;
    }

    public List<String> getDescription() {
        return description;
    }

    public Vector getPosition() {
        return position;
    }

    public int getCount() {
        return count;
    }

    public void OnUse(InventoryClickEvent e) {

    }
}
