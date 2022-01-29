package dev.foton.chat.settings;

import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class ChatPlayer {
    private ChatFormatEnum size = ChatFormatEnum.MEDIUM;
    private AdvancedColor nickColor = new AdvancedColor("00fc00");
    private AdvancedColor splitterColor = new AdvancedColor("333333");
    private AdvancedColor messageColor = new AdvancedColor("829399");
    private String splitter = ": ";

    public TextComponent buildComponent(Player p, String message){
        TextComponent base = Component.text("");

        TextComponent playerComponent = Component.text(p.getName()).color(TextColor.fromCSSHexString("#"+nickColor.getHex()));
        TextComponent splitterComponent = Component.text(splitter).color(TextColor.fromCSSHexString("#"+splitterColor.getHex()));
        TextComponent messageComponent = Component.text(message).color(TextColor.fromCSSHexString("#"+messageColor.getHex()));
        switch (size) {
           case SMALL -> {
                base = base.append(playerComponent)
                        .append(splitterComponent)
                        .append(messageComponent);
            }
            case MEDIUM -> {
                base = base.append(Component.text("[HUB]").color(TextColor.fromCSSHexString("#333333")))
                    .append(Component.text(" "))
                    .append(playerComponent)
                    .append(Component.text(" "))
                    .append(splitterComponent)
                    .append(Component.text(" "))
                    .append(messageComponent);
            }
            case NORMAL -> {

            }
        }

        return base;
    }

    public ChatFormatEnum getSize() {
        return size;
    }

    public void setSize(ChatFormatEnum size) {
        this.size = size;
    }

    public AdvancedColor getNickColor() {
        return nickColor;
    }

    public void setNickColor(AdvancedColor nickColor) {
        this.nickColor = nickColor;
    }

    public AdvancedColor getSplitterColor() {
        return splitterColor;
    }

    public void setSplitterColor(AdvancedColor splitterColor) {
        this.splitterColor = splitterColor;
    }

    public AdvancedColor getMessageColor() {
        return messageColor;
    }

    public void setMessageColor(AdvancedColor messageColor) {
        this.messageColor = messageColor;
    }

    public String getSplitter() {
        return splitter;
    }

    public void setSplitter(String splitter) {
        this.splitter = splitter;
    }
}
