package com.nitkanikita.interfaces.input.chat;

import com.nitkanikita.interfaces.input.BaseRequestInput;
import dev.foton.chat.StyleProfilesManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class ChatInput extends BaseRequestInput {
    protected ChatInput(Player target, BiPredicate<Player, String> callback) {
        super(target, callback);
    }

    List<String> questions = new ArrayList<>();

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<String> getQuestions() {
        return questions;
    }

    @Override
    public void request(boolean err) {
        for (String question : questions) {
            StyleProfilesManager.getProfile(target).systemMessage(question);
        }
    }
}
