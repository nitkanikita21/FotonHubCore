package dev.foton.hubcore.mechanics.servermanager;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.foton.hubcore.Main;
import org.bukkit.entity.Player;

public class ServerConnectionManager {
    public static void connect(Player p, String server){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF("roblabla");
        out.writeUTF(server);

        // If you don't care about the player
        // Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        // Else, specify them
        p.sendPluginMessage(Main.i, "BungeeCord", out.toByteArray());
    }
}
