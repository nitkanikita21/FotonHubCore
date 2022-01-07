package dev.foton.hubcore;

import dev.foton.hubcore.mechanics.MicroMechanicsListener;
import dev.foton.hubcore.modules.commands.CustomCommandBuilder;
import dev.foton.hubcore.modules.interfaces.MenuListener;
import dev.foton.hubcore.modules.interfaces.MenuManager;
import dev.foton.hubcore.modules.interfaces.items.SelectButton;
import dev.foton.hubcore.modules.interfaces.items.Text;
import dev.foton.hubcore.modules.interfaces.items.sub.ImageLabel;
import dev.foton.hubcore.modules.interfaces.items.sub.ScriptableButton;
import dev.foton.hubcore.modules.interfaces.menu.DispancerMenu;
import me.NitkaNikita.AdvancedColorAPI.api.types.builders.GradientTextBuilder;
import me.NitkaNikita.AdvancedColorAPI.api.types.builders.SolidTextBuilder;
import me.nitkanikita.particlevisualeffects.ParticleModuleListener;
import me.nitkanikita.particlevisualeffects.effectengine.RenderEffectRunnable;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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

        //#region REMOVE
        getServer().getLogger().info(ChatColor.GREEN+"[!!!] SERVER READY TO WORK");
        getServer().getLogger().info(ChatColor.GREEN+"[!!!] SERVER READY TO WORK");

        DispancerMenu testMenu = new DispancerMenu(new GradientTextBuilder()
                .text("&lDUNGEON MASTER")
                .addColor("#42f5a4").addColor("#fc0373")
                .blur(0.4)
                .build().getJsonText(), "test_menu");

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

        SelectButton selectBtn = new SelectButton(Material.BOOK ,"&aSelect variant","select",new Vector(1,1,1),1);

        selectBtn.addVar(
                new GradientTextBuilder()
                        .text("&lDUNGEON MASTER")
                        .addColor("#fc8003").addColor("#fc0373")
                        .blur(0.4)
                        .build().getJsonText()
        );

        selectBtn.addVar("eSports");
        selectBtn.addVar("HARD");
        selectBtn.addVar(new SolidTextBuilder().text("&l&oPRO").color("#42f5a4").build().getJsonText());
        selectBtn.addVar("Medium");
        selectBtn.addVar("Normal");
        selectBtn.addVar("&aEasy");
        selectBtn.addVar("noob");
        selectBtn.addVar("very noob");

        ImageLabel l = new ImageLabel(Material.GREEN_TERRACOTTA,"&lOk and","img",new Vector(3,1,1));

        testMenu.addElement(l);
        testMenu.addElement(btnOp);
        testMenu.addElement(btnGm);
        testMenu.addElement(test_lable);
        testMenu.addElement(selectBtn);
        MenuManager.addMenu(testMenu);

        //#endregion

        CustomCommandBuilder.setPlugin(this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this,new RenderEffectRunnable(),0,1);

        getServer().getPluginManager().registerEvents(new MenuListener(),this);
        getServer().getPluginManager().registerEvents(new ParticleModuleListener(),this);
        getServer().getPluginManager().registerEvents(new MicroMechanicsListener(),this);

        //#region тоже удалить

        new CustomCommandBuilder()
                .name("menu").executor((commandSender, strings) -> {
            if (commandSender instanceof Player){
                MenuManager.open((Player) commandSender,MenuManager.getMenu("test_menu"));
            }
        }).registryPlugin();
        //#endregion

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
