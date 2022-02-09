package dev.foton.chat;

import dev.foton.hubcore.modules.interfaces.input.chat.ChatInputListener;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    private final ChatInputListener listener = new ChatInputListener();

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
                StyleProfilesManager.getProfile(player).chatMessage(e.getPlayer(),PlainTextComponentSerializer.plainText().serialize(e.message()));
            }
        }else {
            e.setCancelled(true);
        }
    }
}
