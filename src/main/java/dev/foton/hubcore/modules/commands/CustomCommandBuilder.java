package dev.foton.hubcore.modules.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.BiConsumer;

public class CustomCommandBuilder {
    private static JavaPlugin plugin;
    public static void setPlugin(JavaPlugin p){
        plugin = p;
    }

    private String name;
    private BiConsumer<CommandSender, String[]> lambda;

    public CustomCommandBuilder name(String name){
        this.name = name;
        return this;
    }

    public CustomCommandBuilder executor(BiConsumer<CommandSender, String[]> executor){
        this.lambda = executor;
        return this;
    }

    public CommandExecutor build(){
        return new CustomCommand(lambda);
    }

    public CustomCommandBuilder registryPlugin(){
        plugin.getCommand(name).setExecutor(new CustomCommand(lambda));
        return this;
    }
}
