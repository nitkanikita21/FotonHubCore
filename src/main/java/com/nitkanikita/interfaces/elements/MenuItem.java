package com.nitkanikita.interfaces.elements;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class MenuItem extends ItemStack {

    public MenuItem(@NotNull ItemStack stack) throws IllegalArgumentException {
        super(stack);
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    protected boolean cancelable = true;

}
