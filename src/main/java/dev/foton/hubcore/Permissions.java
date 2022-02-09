package dev.foton.hubcore;

public enum Permissions {
    ADMIN_PANEL("hub.menu.admin");

    String permission;
    Permissions(String s) {
        permission = s;
    }

    @Override
    public String toString() {
        return permission;
    }
}
