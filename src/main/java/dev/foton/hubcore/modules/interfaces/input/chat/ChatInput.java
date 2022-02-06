package dev.foton.hubcore.modules.interfaces.input.chat;

import dev.foton.hubcore.modules.interfaces.input.BaseRequestInput;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class ChatInput extends BaseRequestInput {
    protected ChatInput(Player target, BiPredicate<Player, String> callback) {
        super(target, callback);
    }

    List<Component> questions = new ArrayList<Component>();

    public void setQuestions(List<Component> questions) {
        this.questions = questions;
    }

    public List<Component> getQuestions() {
        return questions;
    }

    @Override
    public void request(boolean err) {
        for (Component question : questions) {
            target.sendMessage(question);
        }
    }
}
