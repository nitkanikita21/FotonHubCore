package com.nitkanikita.interfaces.input;

import org.bukkit.entity.Player;

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

    public abstract void request(boolean err);
}
