package dev.foton.hubcore.mechanics.hats;

import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class Hat {
    protected String name;
    protected String id;
    protected AdvancedColor colorName;
    protected String description;
    protected Material icon;
    protected HatsCollection collection;
    protected double chance;


    public Hat(String id, double chance, HatsCollection collection){
        this.id = id;
        this.collection = collection;
        this.chance = chance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColorName(AdvancedColor colorName) {
        this.colorName = colorName;
    }

    public String getId() {
        return id;
    }

    public HatsCollection getCollection() {
        return collection;
    }

    public String getName() {
        return name;
    }

    public Material getIcon() {
        return icon;
    }

    public AdvancedColor getColorName() {
        return colorName;
    }

    public String getDescription() {
        return description;
    }
}
