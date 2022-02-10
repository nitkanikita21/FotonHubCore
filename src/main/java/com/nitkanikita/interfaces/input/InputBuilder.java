package com.nitkanikita.interfaces.input;

import org.bukkit.entity.Player;

import java.util.function.BiPredicate;

public interface InputBuilder{
    public InputBuilder target(Player t);
    public InputBuilder addQuestion(String question);
    public InputBuilder callback(BiPredicate<Player, String> callback);
    public BaseRequestInput build();
}
