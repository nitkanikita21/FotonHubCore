package com.nitkanikita.interfaces.input;

import org.bukkit.entity.Player;

import java.util.function.BiPredicate;

public interface InputBuilder{
    InputBuilder target(Player t);
    InputBuilder addQuestion(String question);
    InputBuilder callback(BiPredicate<Player, String> callback);
    BaseRequestInput build();
}
