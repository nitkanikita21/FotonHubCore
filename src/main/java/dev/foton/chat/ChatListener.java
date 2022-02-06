package dev.foton.chat;

import dev.foton.hubcore.modules.interfaces.input.chat.ChatInputListener;
import dev.foton.hubcore.modules.interfaces.items.Text;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jline.utils.Log;

public class ChatListener implements Listener {

    private ChatInputListener listener;

    @EventHandler
    public void onChatMessage(AsyncChatEvent e) {
        /*TextComponent base = Component.text("");
        base = base.append(Component.text(player.getName(), TextColor.fromCSSHexString("#00сс00")));
        base = base.append(Component.text(": ", TextColor.fromCSSHexString("#333333")));
        base = base.append(e.originalMessage().color(TextColor.fromCSSHexString("#cccccc")));
        e.setCancelled(true);
        player.sendMessage(base);*/

        if(listener.check(e)){
            e.setCancelled(true);
            for (Player player : Bukkit.getOnlinePlayers()) {
                ChatManager.getChatPlayer(player).chatMessage(PlainTextComponentSerializer.plainText().serialize(e.message()));
            }
        }
    }
}
