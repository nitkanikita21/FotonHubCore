package dev.foton.hubcore.modules.interfaces.items.sub;

import dev.foton.hubcore.modules.interfaces.items.Text;
import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.builders.SolidTextBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageLabel extends Text {
    public ImageLabel(Material icon, String displayName, String id, Vector position) {
        super(icon, displayName, id, new ArrayList<>(), position, 1);
    }

    @Override
    public List<String> getDescription() {
        URL url;
        try {
            url = new URL("https://www.spigotmc.org/data/avatars/s/1407/1407272.jpg?1631631509");
            BufferedImage c = ImageIO.read(url);

            List<String> lore = new ArrayList<>();

            for (int y = 0; y < c.getHeight(); y++) {
                TextComponent line = new TextComponent();

                for (int x = 0; x < c.getWidth(); x++) {
                    Color color = new Color(c.getRGB(x, y));
                    if(color.getRGB() != 0 || color.getRGB() >= 255){
                        line.addExtra(new SolidTextBuilder().text("▉").color(new AdvancedColor(
                                color
                        )).build().renderComponent());
                    }else {
                        line.addExtra("  ");
                    }
                }

                lore.add(line.toLegacyText());
            }

            return lore;

        }catch (MalformedURLException e){
            e.printStackTrace();
            return description;
        } catch (IOException e) {
            e.printStackTrace();
            return description;
        }
    }
}
