package dev.foton.hubcore.modules.interfaces.input;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public abstract class BaseRequestInput {
    protected Player target;
    protected BiPredicate<Player,String> callback;

    protected BaseRequestInput(Player target, BiPredicate<Player, String> callback){
        this.target = target;
        this.callback = callback;
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public void setCallback(BiPredicate<Player, String> callback) {
        this.callback = callback;
    }

    public BiPredicate<Player, String> getCallback() {
        return callback;
    }

    public void request(boolean err){

    }
}
