package dev.foton.hubcore.modules.interfaces;


import dev.foton.hubcore.modules.interfaces.types.IMenu;
import dev.foton.hubcore.modules.interfaces.types.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {
    private static Map<String, IMenu> menus = new HashMap<>();

    public static IMenu getMenu(String id){
        boolean b = menus.containsKey(id);
        return menus.get(id);
    }
    public static void addMenu(Menu menu) {
        if(!menus.containsKey(menu.getId())){
            menus.put(menu.getId(),menu);
        }else {
            menus.remove(menus.get(menu));
            menus.put(menu.getId(),menu);
        }
    }
    public static IMenu removeMenu(String id){
        return menus.remove(id);
    }

    public static void open(Player p, IMenu menu){
        p.openInventory(menu.getInventory());
    }
}
