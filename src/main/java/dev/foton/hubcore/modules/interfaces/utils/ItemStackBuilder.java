package dev.foton.hubcore.modules.interfaces.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemStackBuilder {
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    /**
     * Material-based constructor
     * @param material the material of the item you are building.
     * @param amount   the amount of items in the item you are building.
     */
    public ItemStackBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    /**
     * Material-based constructor
     * @param material the material of the item you are building.
     */
    public ItemStackBuilder(Material material) {
        this(material, 1);
    }

    /**
     * ItemStack-based constructor
     * @param itemStack old ItemStack
     * @param amount    new amount of items in the ItemStack
     */
    public ItemStackBuilder(ItemStack itemStack, int amount) {
        this.itemStack = itemStack;
        this.itemStack.setAmount(amount);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    /**
     * ItemStack-based constructor
     * @param itemStack old ItemStack
     */
    public ItemStackBuilder(ItemStack itemStack) {
        this(itemStack, itemStack.getAmount());
    }

    private void updateItemMeta() {
        this.itemStack.setItemMeta(this.itemMeta);
    }

    /**
     * Sets the display name of the item, this name can be viewed by hovering over the * item in your inventory or holding it in your hand.
     * @param name the name to set for the item.
     * @return the ItemBuilder.
     */
    public ItemStackBuilder setDisplayName(Component name) {

        Map<TextDecoration, TextDecoration.State> decorations = name.decorations();
        decorations.put(TextDecoration.ITALIC, TextDecoration.State.FALSE);
        name = name.decorations(decorations).compact();

        this.itemMeta.displayName(name);
        return this;
    }

    /**
     * @param lines the strings to set as the item lore.
     * @return the ItemBuilder.
     */
    public ItemStackBuilder setLore(Component... lines) {
        for (int i = 0; i < lines.length; i++) {
            Component line = lines[i];

            Map<TextDecoration, TextDecoration.State> decorations = line.decorations();
            decorations.put(TextDecoration.ITALIC, TextDecoration.State.FALSE);
            lines[i] = line.decorations(decorations).compact();

        }
        this.itemMeta.lore(Arrays.asList(lines));
        return this;
    }

    /**
     * @param lore the strings list to set as the item lore.
     * @return the ItemBuilder.
     */
    public ItemStackBuilder setLore(List<Component> lore) {
        for (int i = 0; i < lore.size(); i++) {
            Component line = lore.get(i);

            Map<TextDecoration, TextDecoration.State> decorations = line.decorations();
            decorations.put(TextDecoration.ITALIC, TextDecoration.State.FALSE);
            lore.set(i, line.decorations(decorations).compact());

        }

        this.itemMeta.lore(lore);
        return this;
    }

    /**
     * @param line new lone at the lore
     * @return the ItemBuilder
     */
    public ItemStackBuilder addLore(Component line) {

        Map<TextDecoration, TextDecoration.State> decorations = line.decorations();
        decorations.put(TextDecoration.ITALIC, TextDecoration.State.FALSE);
        line = line.decorations(decorations).compact();

        List<Component> lore = this.itemMeta.lore();
        assert lore != null;
        lore.add(line);
        this.itemMeta.lore(lore);
        return this;
    }

    /**
     * Sets the ItemStack being unbreakable
     * @param unbreakable if the result ItemStack is unbreakable
     * @return the ItemBuilder
     */
    public ItemStackBuilder setUnbreakable(boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Sets visibility of Unbreakable tag
     * @param visible if tag is visible
     * @return the ItemBuilder
     */
    public ItemStackBuilder setUnbreakableTagVisible(boolean visible) {
        if (visible)
            this.itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        else
            this.itemMeta.removeItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        return this;
    }

    /**
     * Set itemflags which should be ignored when rendering a ItemStack in the Client. This Method does silently ignore double set itemFlags.
     * @param itemFlags set of ItemFlags
     * @return the ItemBuilder
     */
    public ItemStackBuilder addItemFlags(ItemFlag ...itemFlags) {
        this.itemMeta.addItemFlags(itemFlags);
        return this;
    }

    /**
     * Remove specific set of itemFlags. This tells the Client it should render it again. This Method does silently ignore double removed itemFlags.
     * @param itemFlags set of ItemFlags
     * @return the ItemBuilder
     */
    public ItemStackBuilder removeItemFlags(ItemFlag ...itemFlags) {
        this.itemMeta.removeItemFlags(itemFlags);
        return this;
    }

    /**
     * Sets the color of the ItemStack.
     * Does nothing if the ItemStack can't be dyed
     * @param color new Color
     * @return the ItemBuilder
     */
    public ItemStackBuilder setColor(Color color) {
        if (itemMeta instanceof LeatherArmorMeta)
            ((LeatherArmorMeta) itemMeta).setColor(color);
        return this;
    }

    /**
     * Sets visibility of Color tag
     * @param visible if tag is visible
     * @return the ItemBuilder
     */
    public ItemStackBuilder setColorTagVisible(boolean visible) {
        if (visible)
            this.itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        else
            this.itemMeta.removeItemFlags(ItemFlag.HIDE_DYE);
        return this;
    }

    /**
     * Sets the owner of the skull
     * Does nothing if the ItemStack is not a skull
     * @param owner skull owner
     * @return the ItemBuilder
     */
    public ItemStackBuilder setOwner(String owner) {
        if (this.itemMeta instanceof SkullMeta)
            ((SkullMeta) this.itemMeta).setOwner(owner);
        return this;
    }

    /**
     * @return the ItemStack that has been created.
     */
    public ItemStack build() {
        updateItemMeta();
        return this.itemStack;
    }

    /**
     * @return the ItemMeta
     */
    public ItemMeta getItemMeta() {
        return itemMeta;
    }
}
