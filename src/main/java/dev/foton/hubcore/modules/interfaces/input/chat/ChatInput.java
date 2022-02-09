package dev.foton.hubcore.modules.interfaces.input.chat;

import dev.foton.chat.StyleProfilesManager;
import dev.foton.hubcore.modules.interfaces.input.BaseRequestInput;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class ChatInput extends BaseRequestInput {
    protected ChatInput(Player target, BiPredicate<Player, String> callback) {
        super(target, callback);
    }

    List<String> questions = new ArrayList<String>();

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<String> getQuestions() {
        return questions;
    }

    @Override
    public void request(boolean err) {
        for (String question : questions) {
            // target.sendMessage(question);

            StyleProfilesManager.getProfile(target).systemMessage(question);
        }
    }
}
