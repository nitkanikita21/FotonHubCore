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

        HatsCollection hell = new HatsCollection("hell");
        hell.setIcon(Material.GREEN_TERRACOTTA);
        hell.setName("Адская коллекция");


        CommonHat common1 = new CommonHat("hell:common1", hell);
        common1.setIcon(Material.MAGMA_BLOCK);
        common1.setName("Пажилая адская шапка");
        hell.addHat(common1);

        addHatCollection(hell);
    }
}
