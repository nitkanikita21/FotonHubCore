package dev.foton.chat.settings;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerStyleProfile {
    public Player player;
    private Map<Styles, String> options = new HashMap<Styles, String>();

    public void setOption(Styles style, String option) {
        options.put(style, option);
    }

    public String getOption(Styles style) {
        return options.get(style);
    }

    public PlayerStyleProfile(Player p){
        player = p;

        //Changeable colors
        options.put(Styles.GENERAL_COLOR,
                "#a5f252"
        );
        options.put(Styles.SUB_COLOR,
                "#5B5F97"
        );
        options.put(Styles.SPECIAL_COLOR,
                "#EFF7FF"
        );
        options.put(Styles.GRAY_COLOR,
                "#44344F"
        );

        //Non changeable colors
        options.put(Styles.INFO_COLOR,
                "#00ff00"
        );
        options.put(Styles.ERROR_COLOR,
                "#BF211E"
        );
        options.put(Styles.WARNING_COLOR,
                "#F7B32B"
        );
        options.put(Styles.DEBUG_COLOR,
                "#0011ff"
        );


        //Others
        options.put(Styles.ARROW,
                ">"
        );
    }

    public TextComponent renderChatMessage(Player author, String content){
        TextComponent start = Component.empty();

        String prefix = PlaceholderAPI.setPlaceholders(author, "%luckperms_prefix%");
        String suffix = PlaceholderAPI.setPlaceholders(author, "%luckperms_suffix%");

        String color = ((String) options.get(Styles.GENERAL_COLOR));

        start = start.append(Component.text(prefix.equals("") ? "" : prefix+" "));
        start = start.append(Component.text(author.getName()+" ",TextColor.fromCSSHexString(
                color
        )));
        start = start.append(Component.text(suffix+" "));

        start = start.append(
                Component.text((String)options.get(Styles.ARROW)+" ",
                        TextColor.fromCSSHexString(
                                (String)options.get(Styles.SUB_COLOR)
                        ))
        );

        start = start.append(Component.text(content,TextColor.fromCSSHexString(
                ((String) options.get(Styles.SPECIAL_COLOR))
        )));

        return start;
    }

    public TextComponent renderSystemMessage(String message, SystemMessageType type){
        TextComponent start = Component.empty();

        start = start.append(Component.text(
                "[Система]",
                TextColor.fromHexString(options.get(Styles.GENERAL_COLOR))
        ));
        start = start.append(Component.space());
        start = start.append(Component.text(
                "["+type.name()+"]",
                TextColor.fromHexString(options.get(Styles.valueOf(type.name()+"_COLOR")))
        ));

        start = start.append(Component.space());
        start = start.append(Component.text(
                message,
                TextColor.fromHexString(options.get(Styles.SPECIAL_COLOR))
        ));

        return start;
    }

    public void chatMessage(Player author, String content){
        player.sendMessage(renderChatMessage(author,content));
    }

    public void systemMessage(String message, SystemMessageType type){
        player.sendMessage(renderSystemMessage(message, type));
    }
    public void systemMessage(String message){
        player.sendMessage(renderSystemMessage(message, SystemMessageType.INFO));
    }



    public enum Styles {
        GENERAL_COLOR,
        SUB_COLOR,
        SPECIAL_COLOR,
        GRAY_COLOR,

        INFO_COLOR,
        ERROR_COLOR,
        WARNING_COLOR,
        DEBUG_COLOR,

        ARROW,
    }
    public enum SystemMessageType{
        INFO,
        WARNING,
        ERROR,
        DEBUG
    }
}
