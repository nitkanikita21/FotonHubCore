package dev.foton.hubcore;

import dev.foton.chat.StyleProfilesManager;
import dev.foton.chat.settings.PlayerStyleProfile;
import com.nitkanikita.interfaces.Point;
import com.nitkanikita.interfaces.elements.Button;
import com.nitkanikita.interfaces.menus.ChestMenu;
import com.nitkanikita.interfaces.menus.Menu;
import com.nitkanikita.interfaces.utils.ItemStackBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuPrefabs {

    private MenuPrefabs() { throw new IllegalStateException("Utility class"); }


    public static Menu generalMenu(Player viewer){
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
                        .build()
        ){
            @Override
            public void event(InventoryClickEvent e) {
                settingsMenu(viewer).openMenu(viewer);
            }
        });

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
                        .build()
        ){
            @Override
            public void event(InventoryClickEvent e) {
                e.getWhoClicked().sendMessage(Component.text(
                        "В разработке",
                        TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.GRAY_COLOR))
                ));
            }
        });

        chestMenu.setSlot(new Point(7,1), new Button(
                new ItemStackBuilder(Material.NETHER_STAR)
                        .setDisplayName(Component.text(
                                "Прочее",
                                TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.GENERAL_COLOR))
                        ))
                        .build()
        ){
            @Override
            public void event(InventoryClickEvent e) {
                e.getWhoClicked().sendMessage(Component.text(
                        "В разработке",
                        TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.GRAY_COLOR))
                ));
            }
        });

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
                            .build()
            ){
                @Override
                public void event(InventoryClickEvent e) {
                    adminMenu(viewer).openMenu(viewer);
                }
            });
        }

        return chestMenu;
    }

    public static Menu adminMenu(Player viewer){
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
                        .build()
        ){
            @Override
            public void event(InventoryClickEvent e) {
                profile.systemMessage(
                        "Внимание! Начинается перезагрузка сервера",
                        PlayerStyleProfile.SystemMessageType.WARNING
                );
                viewer.closeInventory();
                Bukkit.reload();
            }
        });

        return menu;
    }

    public static Menu settingsMenu(Player viewer){
        PlayerStyleProfile profile = StyleProfilesManager.getProfile(viewer);

        ChestMenu menu = new ChestMenu(3,Component.text(
                "Настройки",
                TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.GENERAL_COLOR))
        ));

        menu.setSlot(new Point(7,1), new Button(
                new ItemStackBuilder(Material.NETHER_STAR)
                        .setDisplayName(Component.text(
                                "Прочее",
                                TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.GENERAL_COLOR))
                        ))
                        .build()
        ){
            @Override
            public void event(InventoryClickEvent e) {
                e.getWhoClicked().sendMessage(Component.text(
                        "В разработке",
                        TextColor.fromHexString(profile.getOption(PlayerStyleProfile.Styles.GRAY_COLOR))
                ));
            }
        });

        return menu;
    }
}
