package dev.foton.hubcore.modules.interfaces.old_api;


import dev.foton.hubcore.modules.interfaces.old_api.types.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public final class MenuManager {
    private static Map<String, Menu> menus = new HashMap<>();

    public static Menu getMenu(String id){
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

    public static Menu removeMenu(String id){
        return menus.remove(id);
    }

    public static void open(Player p, Menu menu){
        p.openInventory(menu.getInventory());
    }
}
