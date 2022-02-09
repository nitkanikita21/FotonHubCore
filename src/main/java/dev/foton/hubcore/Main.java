package dev.foton.hubcore;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import dev.foton.chat.ChatListener;
import dev.foton.chat.StyleProfilesManager;
import dev.foton.chat.settings.PlayerStyleProfile;
import dev.foton.hubcore.mechanics.MicroMechanicsListener;
import dev.foton.hubcore.mechanics.hats.HatsManager;
import dev.foton.hubcore.mechanics.npc.NPCManager;
import dev.foton.hubcore.mechanics.servermanager.ServerConnectionManager;
import dev.foton.hubcore.modules.commands.CustomCommandBuilder;
import dev.foton.hubcore.modules.interfaces.InventoryListener;
import dev.foton.hubcore.modules.interfaces.Point;
import dev.foton.hubcore.modules.interfaces.elements.Button;
import dev.foton.hubcore.modules.interfaces.menus.ChestMenu;
import dev.foton.hubcore.modules.interfaces.utils.ItemStackBuilder;
import me.nitkanikita.particlevisualeffects.ParticleModuleListener;
import me.nitkanikita.particlevisualeffects.effectengine.RenderEffectRunnable;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main i;

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }
    public LuckPerms getLuckperms() {
        return luckperms;
    }


    private ProtocolManager protocolManager;
    private LuckPerms luckperms;

    @Override
    public void onEnable() {
        // Plugin startup logic
        i = this;

        protocolManager = ProtocolLibrary.getProtocolManager();

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckperms = provider.getProvider();
        }

        HatsManager.init();
        //initMenus();

        CustomCommandBuilder.setPlugin(this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this,new RenderEffectRunnable(),0,1);

        PluginManager pluginManager = getServer().getPluginManager();
        //pluginManager.registerEvents(new MenuListener(),this);
        pluginManager.registerEvents(new InventoryListener(),this);
        pluginManager.registerEvents(new ParticleModuleListener(),this);
        pluginManager.registerEvents(new MicroMechanicsListener(),this);
        pluginManager.registerEvents(new ChatListener(),this);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if(!StyleProfilesManager.checkRegistry(p)){
                StyleProfilesManager.setProfile(p);
            }
        }

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        new CustomCommandBuilder()
                .name("games").executor((commandSender, strings) -> {
            if (commandSender instanceof Player){
                //MenuManager.open((Player) commandSender,MenuManager.getMenu("menu_go_to"));
            }
        }).registryPlugin();

        new CustomCommandBuilder()
                .name("menu").executor((commandSender, strings) -> {
            if (commandSender instanceof Player){

                MenuPrefabs.GENERAL_MENU((Player) commandSender).openMenu((Player) commandSender);

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
                .name("admin").executor((commandSender, strings) -> {
            if (commandSender instanceof Player){
                MenuPrefabs.ADMIN_MENU((Player) commandSender).openMenu((Player) commandSender);
            }
        }).registryPlugin();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    /*@Deprecated
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
        campfireBtn.addDescriptionLine(
                new SolidTextBuilder()
                        .color("#42f5a4")
                        .text("Campfire")
                        .build()
                        .getJsonText() +
                        "&7 - Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
        );
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
                MenuManager.open((Player) e.getWhoClicked(),MenuManager.getMenu("customazise_theme"));
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
                                new SolidTextBuilder()
                                        .text(hat.getName())
                                        .color(hat.getColorName())
                                        .build()
                                        .getJsonText(),
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


        ChestMenu customizeChat = new ChestMenu(
                new SolidTextBuilder()
                        .text("Настройки темы")
                        .color("#00ff00")
                        .build()
                        .getJsonText(),
                "customazise_theme",
                2
        );

        ScriptableButton generalColor = new ScriptableButton(
                Material.LIME_DYE,
                "&2Установить главный цвет",
                "generalColor",
                new Vector(1, 1, 1),
                1
        ) {
            @Override
            public void OnUse(InventoryClickEvent e) {
                BaseRequestInput requestInput = new ChatInputBuilder()
                        .target((Player) e.getWhoClicked())
                        .addQuestion("Введи HEX цвет (например #33ff00):")
                        .callback((player, s) -> {

                            PlayerStyleProfile chatPlayer = ChatManager.getChatPlayer(player);

                            if(s.startsWith("#") && s.length() == 7){
                                chatPlayer.setOption(PlayerStyleProfile.Styles.GENERAL_COLOR,s);
                                chatPlayer.systemMessage("Цвет был успешно применён");

                                return true;
                            }
                            chatPlayer.systemMessage(
                                    "Неверный ввод! Повторите",
                                    PlayerStyleProfile.SystemMessageType.WARNING
                            );

                            return false;
                        })
                        .build();

                InputsManager.sendRequest(requestInput);
            }
        };
        generalColor.addDescriptionLine("&7Нажмите что бы ввести цвет");
        customizeChat.addElement(generalColor);


        ScriptableButton subColor = new ScriptableButton(
                Material.MAGENTA_DYE,
                "&2Установить дополнительный цвет",
                "subColor",
                new Vector(2, 1, 1),
                1
        ) {
            @Override
            public void OnUse(InventoryClickEvent e) {
                BaseRequestInput requestInput = new ChatInputBuilder()
                        .target((Player) e.getWhoClicked())
                        .addQuestion("Введи HEX цвет (например #33ff00):")
                        .callback((player, s) -> {

                            PlayerStyleProfile chatPlayer = ChatManager.getChatPlayer(player);

                            if(s.startsWith("#") && s.length() == 7){
                                chatPlayer.setOption(PlayerStyleProfile.Styles.SUB_COLOR,s);
                                chatPlayer.systemMessage("Цвет был успешно применён");

                                return true;
                            }
                            chatPlayer.systemMessage(
                                    "Неверный ввод! Повторите",
                                    PlayerStyleProfile.SystemMessageType.WARNING
                            );

                            return false;
                        })
                        .build();

                InputsManager.sendRequest(requestInput);
            }
        };
        subColor.addDescriptionLine("&7Нажмите что бы ввести цвет");
        customizeChat.addElement(subColor);


        ScriptableButton specialColor = new ScriptableButton(
                Material.ORANGE_DYE,
                "&2Установить специальный цвет",
                "specialColor",
                new Vector(3, 1, 1),
                1
        ) {
            @Override
            public void OnUse(InventoryClickEvent e) {
                BaseRequestInput requestInput = new ChatInputBuilder()
                        .target((Player) e.getWhoClicked())
                        .addQuestion("Введи HEX цвет (например #33ff00):")
                        .callback((player, s) -> {

                            PlayerStyleProfile chatPlayer = ChatManager.getChatPlayer(player);

                            if(s.startsWith("#") && s.length() == 7){
                                chatPlayer.setOption(PlayerStyleProfile.Styles.SPECIAL_COLOR,s);
                                chatPlayer.systemMessage("Цвет был успешно применён");

                                return true;
                            }
                            chatPlayer.systemMessage(
                                    "Неверный ввод! Повторите",
                                    PlayerStyleProfile.SystemMessageType.WARNING
                            );

                            return false;
                        })
                        .build();

                InputsManager.sendRequest(requestInput);


            }
        };
        specialColor.addDescriptionLine("&7Нажмите что бы ввести цвет");
        customizeChat.addElement(specialColor);

        MenuManager.addMenu(customizeChat);


    }*/
}
