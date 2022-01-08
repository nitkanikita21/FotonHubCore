package dev.foton.hubcore;

import dev.foton.hubcore.mechanics.MicroMechanicsListener;
import dev.foton.hubcore.modules.commands.CustomCommandBuilder;
import dev.foton.hubcore.modules.interfaces.MenuListener;
import dev.foton.hubcore.modules.interfaces.MenuManager;
import dev.foton.hubcore.modules.interfaces.items.Text;
import dev.foton.hubcore.modules.interfaces.items.ToggleButton;
import dev.foton.hubcore.modules.interfaces.items.sub.EmptyElement;
import dev.foton.hubcore.modules.interfaces.items.sub.ScriptableButton;
import dev.foton.hubcore.modules.interfaces.menu.DispancerMenu;
import me.NitkaNikita.AdvancedColorAPI.api.types.builders.GradientTextBuilder;
import me.NitkaNikita.AdvancedColorAPI.api.types.builders.SolidTextBuilder;
import me.nitkanikita.particlevisualeffects.ParticleModuleListener;
import me.nitkanikita.particlevisualeffects.effectengine.RenderEffectRunnable;
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



        DispancerMenu welcomeMenu = new DispancerMenu(new GradientTextBuilder()
                .text("&lКуда отправимся?")
                .addColor("#42f5a4").addColor("#fc0373")
                .blur(0.4)
                .build().getJsonText(), "menu_go_to");

        ScriptableButton campfireBtn = new ScriptableButton(
                Material.CAMPFIRE,
                "&7Режим "+new SolidTextBuilder().color("#42f5a4").text("Campfire").build().getJsonText(),
                "campfireBtn", new ArrayList<>(), new Vector(2,2,1),
                1
        );

        campfireBtn.addDescriptionLine("");
        campfireBtn.addDescriptionLine(new SolidTextBuilder().color("#42f5a4").text("Campfire").build().getJsonText() + "&7 - Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
        campfireBtn.addDescriptionLine("&7Duis id massa efficitur, congue ex quis, rhoncus sem.");
        campfireBtn.addDescriptionLine("&7Nunc bibendum nisl hendrerit tellus ultrices feugiat.");
        campfireBtn.addDescriptionLine("&7Aenean placerat, augue et porta vulputate.");
        campfireBtn.addDescriptionLine("");
        campfireBtn.addDescriptionLine("");
        campfireBtn.addDescriptionLine("&7Сейчас играют &d65 &7игроков");

        welcomeMenu.addElement(campfireBtn);






        MenuManager.addMenu(welcomeMenu);


        CustomCommandBuilder.setPlugin(this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this,new RenderEffectRunnable(),0,1);

        getServer().getPluginManager().registerEvents(new MenuListener(),this);
        getServer().getPluginManager().registerEvents(new ParticleModuleListener(),this);
        getServer().getPluginManager().registerEvents(new MicroMechanicsListener(),this);

        //#region удалить

        new CustomCommandBuilder()
                .name("menu").executor((commandSender, strings) -> {
            if (commandSender instanceof Player){
                MenuManager.open((Player) commandSender,MenuManager.getMenu("menu_go_to"));
            }
        }).registryPlugin();
        //#endregion

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
