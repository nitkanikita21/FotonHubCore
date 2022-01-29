package dev.foton.hubcore;

import dev.foton.chat.ChatListener;
import dev.foton.hubcore.mechanics.MicroMechanicsListener;
import dev.foton.hubcore.mechanics.hats.Hat;
import dev.foton.hubcore.mechanics.hats.HatsCollection;
import dev.foton.hubcore.mechanics.hats.HatsManager;
import dev.foton.hubcore.mechanics.npc.NPCManager;
import dev.foton.hubcore.mechanics.servermanager.ServerConnectionManager;
import dev.foton.hubcore.modules.commands.CustomCommandBuilder;
import dev.foton.hubcore.modules.interfaces.MenuListener;
import dev.foton.hubcore.modules.interfaces.MenuManager;
import dev.foton.hubcore.modules.interfaces.items.Text;
import dev.foton.hubcore.modules.interfaces.items.sub.OpenMenu;
import dev.foton.hubcore.modules.interfaces.items.sub.ScriptableButton;
import dev.foton.hubcore.modules.interfaces.menu.ChestMenu;
import dev.foton.hubcore.modules.interfaces.menu.DispancerMenu;
import me.NitkaNikita.AdvancedColorAPI.api.types.builders.GradientTextBuilder;
import me.NitkaNikita.AdvancedColorAPI.api.types.builders.SolidTextBuilder;
import me.nitkanikita.particlevisualeffects.ParticleModuleListener;
import me.nitkanikita.particlevisualeffects.effectengine.RenderEffectRunnable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    public static Main i;

    public static String format(String s){
        return s.replaceAll("&","\u00A7");
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        i = this;

        HatsManager.init();
        initMenus();

        CustomCommandBuilder.setPlugin(this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this,new RenderEffectRunnable(),0,1);

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new MenuListener(),this);
        pluginManager.registerEvents(new ParticleModuleListener(),this);
        pluginManager.registerEvents(new MicroMechanicsListener(),this);
        pluginManager.registerEvents(new ChatListener(),this);


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
            if (commandSender instanceof Player p){
                NPCManager.getInstance().appendGameNPC(p.getLocation(),strings[0],player -> {
                    p.sendMessage("test");
                    ServerConnectionManager.connect(player,strings[1]);
                });
            }
        }).registryPlugin();

        new CustomCommandBuilder()
                .name("hats").executor((commandSender, strings) -> {
            if (commandSender instanceof Player){
                MenuManager.open((Player) commandSender,MenuManager.getMenu("hats"));
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
                "campfireBtn", new Vector(2,2,1),
                1
        ){
            @Override
            public void OnUse(InventoryClickEvent e) {}
        };

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

        OpenMenu goToGame = new OpenMenu(
                Material.COMPASS,
                "&6Все сервера",
                "allGamesBtn", new Vector(5,2,1),
                1, "menu_go_to"
        );
        OpenMenu hatsBtn = new OpenMenu(
                Material.LEATHER_HELMET,
                "&bШапки",
                "hatsBtn", new Vector(3,2,1),
                1, "hats"
        );


        ScriptableButton settings = new ScriptableButton(
                Material.COMPARATOR,
                "&bНастройки",
                "settingsBtn", new Vector(7,2,1),
                1
        ){
            @Override
            public void OnUse(InventoryClickEvent e) {

            }
        };

        allMenus.addElement(goToGame);
        allMenus.addElement(hatsBtn);
        allMenus.addElement(settings);

        ChestMenu hatsCollectionsMenu = new ChestMenu("&6Шапки","hats",6);

        int x = 1;
        int y = 1;
        List<HatsCollection> collections = new ArrayList<>(HatsManager.getCollectionMap().values());

        for (int i = 0; i < collections.size() && y < hatsCollectionsMenu.getHeight(); i++) {
            if(x > 9) {
                x = 1;
                y++;
            }

            HatsCollection collection = collections.get(i);

            ScriptableButton h = new ScriptableButton(collection.getIcon(),
                    collection.getName(),
                    collection.getId(),
                    new Vector(x,y,1),1
            ){

                @Override
                public void OnUse(InventoryClickEvent e) {

                    ChestMenu hatsMenu = new ChestMenu(collection.getName(),"hats_"+collection.getId(),6);

                    ArrayList<Hat> hats = new ArrayList<>(collection.getHats());


                    int x2 = 1;
                    int y2 = 1;

                    for (int j = 0; j < collections.size() && y2 < hatsMenu.getHeight(); j++) {
                        if(x2 > 9) {
                            x2 = 1;
                            y2++;
                        }

                        Hat hat = hats.get(j);

                        Text hatText = new Text(hat.getIcon(),
                                new SolidTextBuilder().text(hat.getName()).color(hat.getColorName()).build().getJsonText(),
                                hat.getId(),
                                new Vector(x2,y2,1),1
                        );

                        hatsMenu.addElement(hatText);

                        x2++;
                    }

                    MenuManager.addMenu(hatsMenu);

                    MenuManager.open((Player) e.getWhoClicked() ,hatsMenu);
                }
            };

            hatsCollectionsMenu.addElement(h);

            x++;
        }

        MenuManager.addMenu(hatsCollectionsMenu);

        MenuManager.addMenu(allMenus);
        MenuManager.addMenu(welcomeMenu);

    }
}
