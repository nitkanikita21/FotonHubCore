package dev.foton.hubcore;

import dev.foton.hubcore.mechanics.MicroMechanicsListener;
import dev.foton.hubcore.mechanics.npc.NPCManager;
import dev.foton.hubcore.mechanics.servermanager.ServerConnectionManager;
import dev.foton.hubcore.modules.commands.CustomCommandBuilder;
import dev.foton.hubcore.modules.interfaces.MenuListener;
import dev.foton.hubcore.modules.interfaces.MenuManager;
import dev.foton.hubcore.modules.interfaces.items.sub.ScriptableButton;
import dev.foton.hubcore.modules.interfaces.menu.ChestMenu;
import dev.foton.hubcore.modules.interfaces.menu.DispancerMenu;
import me.NitkaNikita.AdvancedColorAPI.api.types.builders.GradientTextBuilder;
import me.NitkaNikita.AdvancedColorAPI.api.types.builders.SolidTextBuilder;
import me.nitkanikita.particlevisualeffects.ParticleModuleListener;
import me.nitkanikita.particlevisualeffects.effectengine.RenderEffectRunnable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
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

        initMenus();

        CustomCommandBuilder.setPlugin(this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this,new RenderEffectRunnable(),0,1);

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new MenuListener(),this);
        pluginManager.registerEvents(new ParticleModuleListener(),this);
        pluginManager.registerEvents(new MicroMechanicsListener(),this);

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        new CustomCommandBuilder()
                .name("games").executor((commandSender, strings) -> {
            if (commandSender instanceof Player){
                MenuManager.open((Player) commandSender,MenuManager.getMenu("menu_go_to"));
            }
        }).registryPlugin();

        new CustomCommandBuilder()
                .name("menu").executor((commandSender, strings) -> {
            if (commandSender instanceof Player){
                MenuManager.open((Player) commandSender,MenuManager.getMenu("menu_all_menu"));
            }
        }).registryPlugin();

        new CustomCommandBuilder()
                .name("spawnnpc").executor((commandSender, strings) -> {
            if (commandSender instanceof Player){
                Player p = (Player) commandSender;
                NPCManager.getInstance().appendGameNPC(p.getLocation(),strings[0],player -> {
                    ServerConnectionManager.connect(player,strings[1]);
                });
            }
        }).registryPlugin();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void initMenus(){
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


        ChestMenu allMenus = new ChestMenu("&6Все меню", "menu_all_menu",3);

        ScriptableButton goToGame = new ScriptableButton(
                Material.COMPASS,
                "&6Все сервера",
                "allGamesBtn", new ArrayList<>(), new Vector(5,2,1),
                1
        );

        goToGame.setScript(humanEntity -> {
            MenuManager.open((Player) humanEntity,MenuManager.getMenu("menu_go_to"));
        });

        ScriptableButton hats = new ScriptableButton(
                Material.LEATHER_HELMET,
                "&bШапки",
                "hatsBtn", new ArrayList<>(), new Vector(3,2,1),
                1
        );

        ScriptableButton settings = new ScriptableButton(
                Material.COMPARATOR,
                "&bНастройки",
                "settingsBtn", new ArrayList<>(), new Vector(7,2,1),
                1
        );

        allMenus.addElement(goToGame);
        allMenus.addElement(hats);
        allMenus.addElement(settings);

        MenuManager.addMenu(allMenus);
        MenuManager.addMenu(welcomeMenu);

    }
}
