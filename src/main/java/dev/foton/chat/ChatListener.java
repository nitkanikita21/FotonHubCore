package dev.foton.chat;

import dev.foton.hubcore.modules.interfaces.items.Text;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChatMessage(AsyncChatEvent e) {
        Player player = e.getPlayer();
        /*TextComponent base = Component.text("");
        base = base.append(Component.text(player.getName(), TextColor.fromCSSHexString("#00сс00")));
        base = base.append(Component.text(": ", TextColor.fromCSSHexString("#333333")));
        base = base.append(e.originalMessage().color(TextColor.fromCSSHexString("#cccccc")));
        e.setCancelled(true);
        player.sendMessage(base);*/

        e.setCancelled(true);
        player.sendMessage(
                ChatManager.getChatPlayer(player)
                        .buildComponent(player, PlainTextComponentSerializer.plainText().serialize(e.message()))
        );
    }
}
