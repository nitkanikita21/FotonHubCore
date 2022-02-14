package dev.foton.hubcore;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import dev.foton.chat.ChatListener;
import dev.foton.chat.StyleProfilesManager;
import dev.foton.hubcore.mechanics.MicroMechanicsListener;
import dev.foton.hubcore.mechanics.hats.HatsManager;
import dev.foton.hubcore.modules.commands.CustomCommandBuilder;
import com.nitkanikita.interfaces.InventoryListener;
import me.nitkanikita.particlevisualeffects.ParticleModuleListener;
import me.nitkanikita.particlevisualeffects.effectengine.RenderEffectRunnable;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {


    public static Main getInstance(){
        return JavaPlugin.getPlugin(Main.class);
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }
    public LuckPerms getLuckperms() {
        return luckperms;
    }


    private ProtocolManager protocolManager;
    private LuckPerms luckperms;

    @Override
    public void onEnable() {
        // Plugin startup logic

        protocolManager = ProtocolLibrary.getProtocolManager();

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckperms = provider.getProvider();
        }

        HatsManager.init();

        CustomCommandBuilder.setPlugin(this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this,new RenderEffectRunnable(),0,1);

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new InventoryListener(),this);
        pluginManager.registerEvents(new ParticleModuleListener(),this);
        pluginManager.registerEvents(new MicroMechanicsListener(),this);
        pluginManager.registerEvents(new ChatListener(),this);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if(!StyleProfilesManager.checkRegistry(p)){
                StyleProfilesManager.setProfile(p);
            }
        }

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        new CustomCommandBuilder()
                .name("menu").executor((commandSender, strings) -> {
            if (commandSender instanceof Player p){

                MenuPrefabs.generalMenu(p).openMenu((Player) commandSender);

            }
        }).registryPlugin();

        new CustomCommandBuilder()
                .name("admin_menu").executor((commandSender, strings) -> {
            if (commandSender instanceof Player p){
                MenuPrefabs.adminMenu(p).openMenu((Player) commandSender);
            }
        }).registryPlugin();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
