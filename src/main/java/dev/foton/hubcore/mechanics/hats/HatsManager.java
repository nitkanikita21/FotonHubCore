package dev.foton.hubcore.mechanics.hats;

import dev.foton.hubcore.mechanics.hats.sub.CommonHat;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class HatsManager {
    protected static Map<String, HatsCollection> collectionMap = new HashMap<>();

    public static Map<String, HatsCollection> getCollectionMap() {
        return collectionMap;
    }

    public static HatsCollection getCollection(String id){
        return collectionMap.get(id);
    }

    public static void addHatCollection(HatsCollection hatsCollection){
        collectionMap.put(hatsCollection.getId(), hatsCollection);
    }

    public static void init(){

        for (int i = 0; i < 5; i++) {
            HatsCollection hell = new HatsCollection("hell"+i);
            hell.setIcon(Material.GREEN_TERRACOTTA);
            hell.setName("Адская коллекция "+i);


            for (int j = 0; j < 6; j++) {
                CommonHat common = new CommonHat("hell:common"+i+""+j, hell);
                common.setIcon(Material.LEATHER_HELMET);
                common.setName("Пажилая адская шапка "+j);
                hell.addHat(common);
            }

            addHatCollection(hell);
        }
    }
}
