package dev.foton.hubcore.modules.interfaces.items;

import dev.foton.hubcore.modules.interfaces.types.MenuItem;
import dev.foton.hubcore.modules.interfaces.types.MenuRefeshItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class SelectButton extends MenuRefeshItem {

    protected ArrayList<String> vars = new ArrayList<>();
    protected int select = 0;

    public SelectButton(Material icon, String displayName, String id, Vector position, int count) {
        super(icon, displayName, id, new ArrayList<>(), position, count);
    }

    public int getSelect() {
        return select;
    }

    @Override
    public void OnUse(InventoryClickEvent e) {
        super.OnUse(e);

        if(e.getClick().isLeftClick()){
            select = (select + 1) > (vars.size()-1) ? 0 : select + 1;
            playSound((Player) e.getWhoClicked(), Sound.BLOCK_PISTON_CONTRACT);
        }else if(e.getClick().isRightClick()){
            select = (select - 1) < 0 ? vars.size()-1 : select - 1;
            playSound((Player) e.getWhoClicked(), Sound.BLOCK_PISTON_EXTEND);
        }
    }

    @Override
    public Sound getClickSound() {
        return null;
    }


    @Override
    public String getDisplayName() {
        return super.getDisplayName();
    }

    @Override
    public List<String> getDescription() {
        List<String> lore = new ArrayList<>();

        for (int i = 0; i < vars.size(); i++) {
            lore.add((i == select ? ChatColor.GREEN + ">" : ChatColor.GRAY + " ")+" "+vars.get(i));
        }

        return lore;
    }

    @Override
    public MenuItem OnUpdate() {
        return this;
    }


    public void addVar(String var){
        vars.add(var);
    }

    public void removeVar(int index){
        vars.remove(index);
    }
}
