package dev.foton.hubcore.mechanics.hats;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;

public class HatsCollection {
    protected String id;
    protected String name;
    protected Material icon;
    protected ArrayList<Hat> hats;

    public HatsCollection(String id){
        this.id = id;
    }


    public void addHat(Hat hat){
        hats.add(hat);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Hat> getHats() {
        return hats;
    }

    public Material getIcon() {
        return icon;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHats(ArrayList<Hat> hats) {
        this.hats = hats;
    }
}
