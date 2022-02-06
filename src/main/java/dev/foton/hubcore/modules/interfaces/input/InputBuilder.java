package dev.foton.hubcore.modules.interfaces.input;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.function.BiPredicate;

public interface InputBuilder{
    public InputBuilder target(Player t);
    public InputBuilder addQuestion(Component question);
    public InputBuilder callback(BiPredicate<Player, String> callback);
    public BaseRequestInput build();
}
