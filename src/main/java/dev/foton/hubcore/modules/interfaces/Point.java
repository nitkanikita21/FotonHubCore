package dev.foton.hubcore.modules.interfaces;

public record Point(int x, int y) {
    public static Point zero(){
        return new Point(0, 0);
    }
    public static Point fast(int x, int y){
        return new Point(x, y);
    }
}
