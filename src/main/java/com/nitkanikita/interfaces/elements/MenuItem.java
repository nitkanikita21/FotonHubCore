package com.nitkanikita.interfaces.elements;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class MenuItem extends ItemStack {

    protected MenuItem(@NotNull ItemStack stack) throws IllegalArgumentException {
        super(stack);
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    protected boolean cancelable = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        if (!super.equals(o)) return false;
        MenuItem menuItem = (MenuItem) o;
        return isCancelable() == menuItem.isCancelable();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isCancelable());
    }
}
