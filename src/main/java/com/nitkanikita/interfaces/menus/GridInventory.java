package com.nitkanikita.interfaces.menus;

import com.nitkanikita.interfaces.Point;
import com.nitkanikita.interfaces.elements.MenuItem;

public interface GridInventory {
    void setSlot(Point slot, MenuItem item);
    MenuItem getSlot(Point slot);
}
