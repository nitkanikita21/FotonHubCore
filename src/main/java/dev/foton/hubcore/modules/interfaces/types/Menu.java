package dev.foton.hubcore.modules.interfaces.types;

import org.bukkit.inventory.Inventory;


import java.util.HashMap;

import java.util.Map;

public abstract class Menu{
    protected String name;
    protected String id;
    protected Map<String,MenuItem> elements = new HashMap<>();

    public Menu(String name, String id){
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return name;
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


    public Inventory getInventory() {
        return null;
    }
}
