package dev.foton.hubcore;

import dev.foton.hubcore.modules.interfaces.InterfacesServerListener;
import dev.foton.hubcore.modules.interfaces.MenuManager;
import dev.foton.hubcore.modules.interfaces.items.Text;
import dev.foton.hubcore.modules.interfaces.menu.DispancerMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    public static Main i;

    public static String format(String s){
        return s.replaceAll("&","§").replace("B","");
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        i = this;

        getServer().getLogger().info(ChatColor.GREEN+"[!!!] SERVER READY TO WORK");
        getServer().getLogger().info(ChatColor.GREEN+"[!!!] SERVER READY TO WORK");

        DispancerMenu testMenu = new DispancerMenu("&3Test Dispancer Menu", "test_menu");
        Text test_lable = new Text(
                Material.BIRCH_SIGN,
                "&3&lHello!",
                "test_lable",
                new ArrayList<>(),
                new Vector(2, 2, 1),
                1
        );
        test_lable.addDescriptionLine("&2this is very cool server!");
        test_lable.addDescriptionLine("&c&l<3");
        testMenu.addElement(test_lable);
        MenuManager.addMenu(testMenu);

        getServer().getPluginManager().registerEvents(new InterfacesServerListener(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
