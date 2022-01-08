package dev.foton.hubcore.modules.interfaces.types;

import dev.foton.hubcore.Main;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.stream.Collectors;

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

    public void setId(String id) {
        this.id = id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public void setPosition(Vector position) {
        this.position = position;
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
        Player p = (Player) e.getWhoClicked();
        if(getClickSound() != null){
            p.playSound(p.getLocation(), getClickSound(),1f,0.7f);
        }
    }

    public void playSound(Player p, Sound s){
        p.playSound(p.getLocation(), s,1f,0.7f);
    }

    public Sound getClickSound(){
        return null;
    }

    public List<String> getLore(){
        return getDescription().stream().map(Main::format).collect(Collectors.toList());
    }
}
