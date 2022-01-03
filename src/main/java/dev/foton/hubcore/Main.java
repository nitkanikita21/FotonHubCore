package dev.foton.hubcore;

import dev.foton.hubcore.modules.interfaces.InterfacesServerListener;
import dev.foton.hubcore.modules.interfaces.MenuListener;
import dev.foton.hubcore.modules.interfaces.MenuManager;
import dev.foton.hubcore.modules.interfaces.items.Text;
import dev.foton.hubcore.modules.interfaces.items.sub.ScriptableButton;
import dev.foton.hubcore.modules.interfaces.menu.DispancerMenu;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    public static Main i;

    public static String format(String s){
        return s.replaceAll("&","\u00A7");
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

        ScriptableButton btnGm = new ScriptableButton(
                Material.DIAMOND,
                "&6Creative",
                "gm",
                new ArrayList<>(),
                new Vector(2, 3, 1),
                1
        );
        btnGm.addDescriptionLine("&7Set your gamemode to &6Creative");
        btnGm.setScript(humanEntity -> {
            humanEntity.setGameMode(GameMode.CREATIVE);
            humanEntity.sendMessage(Main.format("&7Gamemode &6Creative"));
        });

        ScriptableButton btnOp = new ScriptableButton(
                Material.COMMAND_BLOCK,
                "&cOperator",
                "op",
                new ArrayList<>(),
                new Vector(2, 1, 1),
                1
        );
        btnOp.addDescriptionLine("&7Give &cOperator");
        btnOp.setScript(humanEntity -> {
            humanEntity.setOp(true);
            humanEntity.sendMessage(Main.format("&cOperator &7mode active"));
        });


        testMenu.addElement(btnOp);
        testMenu.addElement(btnGm);
        testMenu.addElement(test_lable);
        MenuManager.addMenu(testMenu);

        getServer().getPluginManager().registerEvents(new InterfacesServerListener(),this);
        getServer().getPluginManager().registerEvents(new MenuListener(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
