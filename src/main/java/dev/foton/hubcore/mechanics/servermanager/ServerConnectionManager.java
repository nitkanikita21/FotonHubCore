package dev.foton.hubcore.mechanics.servermanager;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.foton.hubcore.Main;
import org.bukkit.entity.Player;

public class ServerConnectionManager {

    private ServerConnectionManager(){throw new IllegalStateException("Utility class");}

    public static void connect(Player p, String server){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(p.getName());
        out.writeUTF(server);

        p.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
    }
}
