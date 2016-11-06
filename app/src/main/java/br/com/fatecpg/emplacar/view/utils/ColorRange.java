package br.com.fatecpg.emplacar.view.utils;

/**
 * Created by alexandre on 05/11/16.
 */

public class ColorRange {
    private int start;
    private int finish;
    private int color;

    public ColorRange(int start, int finish, int color) {
        this.start = start;
        this.finish = finish;
        this.color = color;
    }

    public ColorRange() {
    }

    public int getStart() {
        return start;
    }

    public int getFinish() {
        return finish;
    }

    public int getColor() {
        return color;
    }

    public boolean isOnRange(int val) {
        return val >= start && val <= finish;
    }
}
