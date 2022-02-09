package dev.foton.hubcore.modules.interfaces.menus;

import dev.foton.hubcore.modules.interfaces.elements.MenuItem;
import dev.foton.hubcore.modules.interfaces.Point;

public interface ChestableMenu {
    void setSlot(Point slot, MenuItem item);
    MenuItem getSlot(Point slot);
}
