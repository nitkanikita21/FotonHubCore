package dev.foton.hubcore.modules.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.function.BiConsumer;

public class CustomCommand implements CommandExecutor {
    public final BiConsumer<CommandSender, String[]> lambda;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        lambda.accept(sender,args);
        return true;
    }

    public CustomCommand(BiConsumer<CommandSender, String[]> script ){
        lambda = script;
    }
}
