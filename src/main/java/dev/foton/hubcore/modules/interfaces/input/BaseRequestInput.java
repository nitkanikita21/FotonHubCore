package dev.foton.hubcore.modules.interfaces.input;

import org.bukkit.entity.Player;

import java.util.function.BiConsumer;

public abstract class BaseRequestInput {
    Player target;
    BiConsumer<Player,String[]> callback;

    protected BaseRequestInput(Player target, BiConsumer<Player, String[]> callback){
        this.target = target;
        this.callback = callback;
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public void setCallback(BiConsumer<Player, String[]> callback) {
        this.callback = callback;
    }

    public BiConsumer<Player, String[]> getCallback() {
        return callback;
    }
}
