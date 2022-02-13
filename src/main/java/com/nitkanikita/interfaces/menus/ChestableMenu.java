package com.nitkanikita.interfaces.menus;

import com.nitkanikita.interfaces.Point;
import com.nitkanikita.interfaces.elements.MenuItem;

public interface ChestableMenu {
    void setSlot(Point slot, MenuItem item);
    MenuItem getSlot(Point slot);
}
