package dev.foton.chat.settings;

import dev.foton.chat.types.OptionContainer;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ChatPlayer {
    public Player player;
    private Map<StyleNameEnum, String> options = new HashMap<StyleNameEnum, String>();

    public void setOption(StyleNameEnum style, String option) {
        options.put(style, option);
    }

    public String getOption(StyleNameEnum style) {
        return options.get(style);
    }

    public ChatPlayer (Player p){
        player = p;

        options.put(StyleNameEnum.MESSAGE_COLOR,
                "#cccccc"
        );

        options.put(StyleNameEnum.NICK_COLOR,
                "#7ea649"
        );

        options.put(StyleNameEnum.SPLITTER_COLOR,
                "#333333"
        );

        options.put(StyleNameEnum.SPLITTER,
                "➠"
        );
    }

    public void chatMessage(String content){
        TextComponent start = Component.empty();

        String prefix = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%");
        String suffix = PlaceholderAPI.setPlaceholders(player, "%luckperms_suffix%");

        String color = ((String) options.get(StyleNameEnum.NICK_COLOR));

        start = start.append(Component.text(prefix.equals("") ? "" : prefix+" "));
        start = start.append(Component.text(player.getName()+" ",TextColor.fromCSSHexString(
                color
        )));
        start = start.append(Component.text(suffix+" "));

        start = start.append(
                Component.text((String)options.get(StyleNameEnum.SPLITTER)+" ",
                        TextColor.fromCSSHexString(
                                (String)options.get(StyleNameEnum.SPLITTER_COLOR)
                        ))
        );

        start = start.append(Component.text(content,TextColor.fromCSSHexString(
                ((String) options.get(StyleNameEnum.MESSAGE_COLOR))
        )));

        player.sendMessage(start);
    }
}
