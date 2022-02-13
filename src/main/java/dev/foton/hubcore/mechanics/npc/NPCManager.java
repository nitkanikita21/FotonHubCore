package dev.foton.hubcore.mechanics.npc;

import com.github.juliarn.npc.NPC;
import com.github.juliarn.npc.NPCPool;
import com.github.juliarn.npc.event.PlayerNPCHideEvent;
import com.github.juliarn.npc.event.PlayerNPCInteractEvent;
import com.github.juliarn.npc.event.PlayerNPCShowEvent;
import com.github.juliarn.npc.modifier.AnimationModifier;
import com.github.juliarn.npc.modifier.EquipmentModifier;
import com.github.juliarn.npc.modifier.LabyModModifier;
import com.github.juliarn.npc.modifier.MetadataModifier;
import com.github.juliarn.npc.profile.Profile;
import dev.foton.hubcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;

public class NPCManager implements Listener {
    private static final NPCManager instance = new NPCManager();

    public static NPCManager getInstance() {
        return instance;
    }

    private final NPCPool npcPool;

    private final Random random;

    public static Map<NPC,Consumer<Player>> scriptsNpcs = new HashMap<>();

    public NPCManager(){
        this.npcPool = NPCPool.builder(Main.i)
                .spawnDistance(60)
                .actionDistance(30)
                .tabListRemoveTicks(20)
                .build();
        this.random = new Random();

        // we are handling NPC events here
        Bukkit.getPluginManager().registerEvents(this, Main.i);

        // making the NPCs randomly sneak and unsneak every second
        Bukkit.getScheduler().runTaskTimer(Main.i, () -> {
            for (NPC npc : this.npcPool.getNPCs()) {
                npc.metadata()
                        .queue(MetadataModifier.EntityMetadata.SNEAKING, this.random.nextBoolean())
                        // sending the change to all players
                        .send();
            }
        }, 20L, 20L);
    }


    public void appendGameNPC(Location location, String name, Consumer<Player> script) {
        Profile profile = new Profile(UUID.fromString("fdef0011-1c58-40c8-bfef-0bdcb1495938"));
        // Synchronously completing the profile, as we only created the profile with a UUID.
        // Through this, the name and textures will be filled in.
        profile.complete();

        // we want to keep the textures, but change the name and UUID.
        profile.setName(name);
        // with a UUID like this, the NPC can play LabyMod emotes!
        profile.setUniqueId(new UUID(this.random.nextLong(), 0));

        // building the NPC
        NPC npc = NPC.builder()
                .profile(profile)
                .location(location)
                .imitatePlayer(false)
                .lookAtPlayer(false)
                // appending it to the NCP pool
                .build(this.npcPool);

        scriptsNpcs.put(npc,script);
    }

    /**
     * Doing something when a NPC is shown for a certain player.
     *
     * @param event The event instance
     */
    @EventHandler
    public void handleNPCShow(PlayerNPCShowEvent event) {
        NPC npc = event.getNPC();

        // sending the data only to the player from the event
        event.send(
                // making the NPC swing its main arm
                npc.animation()
                        .queue(AnimationModifier.EntityAnimation.SWING_MAIN_ARM),
                // making the NPC play the LabyMod "dab" emote.
                // all ids can be found here: https://docs.labymod.net/pages/server/labymod/emote_api/
                npc.labymod()
                        .queue(LabyModModifier.LabyModAction.EMOTE, 3),
                // enabling the skin layers and letting the NPC sneak
                npc.metadata()
                        .queue(MetadataModifier.EntityMetadata.SKIN_LAYERS, true)
                        .queue(MetadataModifier.EntityMetadata.SNEAKING, true));
    }

    /**
     * Doing something when a NPC is hidden for a certain player.
     *
     * @param event The event instance
     */
    @EventHandler
    public void handleNPCHide(PlayerNPCHideEvent event) {
        Player player = event.getPlayer();
        NPC npc = event.getNPC();

        // if the player has been excluded from seeing the NPC, removing him from the excluded players
        if (event.getReason() == PlayerNPCHideEvent.Reason.EXCLUDED) {
            npc.removeExcludedPlayer(player);
        }
    }

    @EventHandler
    public void handleNPCInteract(PlayerNPCInteractEvent event) {
        Player player = event.getPlayer();
        NPC npc = event.getNPC();

        // checking if the player hit the NPC
        if (event.getUseAction() == PlayerNPCInteractEvent.EntityUseAction.ATTACK) {
            // making the NPC look at the player
            scriptsNpcs.get(npc).accept(player);
        }
    }
}
