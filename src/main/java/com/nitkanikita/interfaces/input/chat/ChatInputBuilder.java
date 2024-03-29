package com.nitkanikita.interfaces.input.chat;

import com.nitkanikita.interfaces.input.BaseRequestInput;
import com.nitkanikita.interfaces.input.InputBuilder;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class ChatInputBuilder implements InputBuilder {
    private Player target;
    private final List<String> questions = new ArrayList<>();
    private BiPredicate<Player, String> callback;

    @Override
    public InputBuilder target(Player t) {
        target = t;
        return this;
    }

    @Override
    public InputBuilder addQuestion(String question) {
        questions.add(question);
        return this;
    }

    @Override
    public InputBuilder callback(BiPredicate<Player, String> callback) {
        this.callback = callback;
        return this;
    }

    @Override
    public BaseRequestInput build() {
        ChatInput chatInput = new ChatInput(target,callback);
        chatInput.setQuestions(questions);
        return chatInput;
    }
}
