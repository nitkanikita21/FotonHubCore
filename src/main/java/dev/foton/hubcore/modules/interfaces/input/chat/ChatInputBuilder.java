package dev.foton.hubcore.modules.interfaces.input.chat;

import dev.foton.hubcore.modules.interfaces.input.BaseRequestInput;
import dev.foton.hubcore.modules.interfaces.input.InputBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class ChatInputBuilder implements InputBuilder {
    private Player target;
    private final List<Component> questions = new ArrayList<>();
    private BiPredicate<Player, String> callback;

    @Override
    public InputBuilder target(Player t) {
        target = t;
        return this;
    }

    @Override
    public InputBuilder addQuestion(Component question) {
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
