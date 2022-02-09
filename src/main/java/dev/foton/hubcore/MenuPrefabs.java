package dev.foton.hubcore;

import dev.foton.chat.StyleProfilesManager;
import dev.foton.chat.settings.PlayerStyleProfile;
import dev.foton.hubcore.modules.interfaces.Point;
import dev.foton.hubcore.modules.interfaces.elements.Button;
import dev.foton.hubcore.modules.interfaces.menus.ChestMenu;
import dev.foton.hubcore.modules.interfaces.menus.Menu;
import dev.foton.hubcore.modules.interfaces.utils.ItemStackBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Function;

public class MenuPrefabs {



    public static Menu GENERAL_MENU(Player viewer){
        PlayerStyleProfile profile = StyleProfilesManager.getProfile(viewer);

        ChestMenu chestMenu = new ChestMenu(3, Component.text(
                "Главное меню",
                TextColor.fromHexString("#00ff00")
        ));

        chestMenu.setSlot(new Point(1,1), new Button(
                new ItemStackBuilder(Material.COMPARATOR)
                        .setDisplayName(Component.text(
                                "Настройки",
                                TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.GENERAL_COLOR))
                        ))
                        .build(),
                e -> {

                }
        ));

        chestMenu.setSlot(new Point(4,1), new Button(
                new ItemStackBuilder(Material.COMPASS)
                        .setDisplayName(Component.text(
                                "Наши сервера",
                                TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.GENERAL_COLOR))
                        ))
                        .setLore(Component.text(
                                "У нас чета можна",
                                TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.GRAY_COLOR))
                        ))
                        .build(),
                e -> {

                }
        ));

        chestMenu.setSlot(new Point(7,1), new Button(
                new ItemStackBuilder(Material.NETHER_STAR)
                        .setDisplayName(Component.text(
                                "Прочее",
                                TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.GENERAL_COLOR))
                        ))
                        .build(),
                e -> {

                }
        ));

        if(viewer.hasPermission(Permissions.ADMIN_PANEL+"")){
            chestMenu.setSlot(new Point(4,0), new Button(
                    new ItemStackBuilder(Material.REPEATING_COMMAND_BLOCK)
                            .setDisplayName(Component.text(
                                    "Административное",
                                    TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.DEBUG_COLOR))
                            ))
                            .setLore(Component.text(
                                    "У нас чета можна",
                                    TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.GRAY_COLOR))
                            ))
                            .build(),
                    e -> {

                    }
            ));
        }

        return (Menu) chestMenu;
    }

    public static Menu ADMIN_MENU(Player viewer){
        PlayerStyleProfile profile = StyleProfilesManager.getProfile(viewer);

        ChestMenu menu = new ChestMenu(3,Component.text(
                "Административное",
                TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.DEBUG_COLOR))
        ));
        menu.setSlot(Point.fast(0,0),new Button(
                new ItemStackBuilder(Material.REDSTONE_TORCH)
                        .setDisplayName(Component.text(
                                "Перезагрузка сервера",
                                TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.WARNING_COLOR))
                        ))
                        .setLore(Component.text(
                                "Перезагрузка плагинов сервера",
                                TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.WARNING_COLOR))
                        ))
                        .build(),
                e -> {
                    profile.systemMessage(
                            "Внимание! Начинается перезагрузка сервера",
                            PlayerStyleProfile.SystemMessageType.WARNING
                    );
                    Bukkit.reload();
                }
        ));

        return menu;
    }
}
