package dev.foton.hubcore.modules.interfaces.elements;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class Button extends MenuItem {

    private Sound sound = Sound.sound(
            Key.key("minecraft:ui.button.click"), Sound.Source.MASTER, 0.25f, 1.1f
    );

    public Button(@NotNull ItemStack stack, Consumer<InventoryClickEvent> event) throws IllegalArgumentException {
        super(stack);
        this.event = event;
    }

    public Consumer<InventoryClickEvent> getEvent() {
        return event;
    }

    public Button event(Consumer<InventoryClickEvent> event) {
        this.event = event;
        return this;
    }
    public Button sound(Sound sound){
        this.sound = sound;
        return this;
    }

    private Consumer<InventoryClickEvent> event;

    public void onClick(InventoryClickEvent e) {
        e.getWhoClicked().playSound(sound);
        event.accept(e);
    }
}
