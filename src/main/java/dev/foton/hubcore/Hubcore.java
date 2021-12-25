package dev.foton.hubcore;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hubcore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getLogger().info(ChatColor.GREEN+"[!!!] SERVER READY TO WORK");
        getServer().getLogger().info(ChatColor.GREEN+"[!!!] SERVER READY TO WORK");
        getServer().getLogger().info(ChatColor.GREEN+"[!!!] SERVER READY TO WORK");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
