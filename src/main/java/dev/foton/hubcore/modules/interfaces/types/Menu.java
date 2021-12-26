package dev.foton.hubcore.modules.interfaces.types;

import org.bukkit.inventory.Inventory;


import java.util.HashMap;

import java.util.Map;

public class Menu implements IMenu{
    protected String name;
    protected String id;
    protected Map<String,IMenuItem> elements = new HashMap<>();

    public Menu(String name, String id){
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    public void clearElements(){
        elements.clear();
    }

    @Override
    public void addElement(IMenuItem el) {
        if(!elements.containsKey(el.getId())){
            elements.put(el.getId(),el);
        }
    }

    @Override
    public IMenuItem getElement(String id) {
        return elements.get(id);
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
