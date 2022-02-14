package com.nitkanikita.interfaces.elements;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class Button extends MenuItem {

    private Sound sound = Sound.sound(
            Key.key("minecraft:ui.button.click"), Sound.Source.MASTER, 0.25f, 1.1f
    );

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Button button)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(sound, button.sound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sound);
    }

    protected Button(@NotNull ItemStack stack) throws IllegalArgumentException {
        super(stack);
    }

    public Button sound(Sound sound){
        this.sound = sound;
        return this;
    }

    public abstract void event(InventoryClickEvent e);

    public void onClick(InventoryClickEvent e) {
        e.getWhoClicked().playSound(sound);
        event(e);
    }
}
