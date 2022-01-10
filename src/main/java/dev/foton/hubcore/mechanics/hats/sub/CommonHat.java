package dev.foton.hubcore.mechanics.hats.sub;

import dev.foton.hubcore.mechanics.hats.Hat;
import dev.foton.hubcore.mechanics.hats.HatsCollection;
import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;

public class CommonHat extends Hat {
    public CommonHat(String id, double chance, HatsCollection collection) {
        super(id, chance, collection);
        colorName = new AdvancedColor("#61807e");
    }
}
