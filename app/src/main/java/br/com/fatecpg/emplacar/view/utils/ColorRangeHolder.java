package br.com.fatecpg.emplacar.view.utils;


import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by alexandre on 05/11/16.
 */
public class ColorRangeHolder {
    private ArrayList<ColorRange> colorRanges = new ArrayList<>();


    public int getColor(int val) {
        for (ColorRange colorRange : colorRanges) {
            if (colorRange.isOnRange(val))
                return colorRange.getColor();
        }

        return Color.WHITE;
    }


    public void add(ColorRange colorRange){
        colorRanges.add(colorRange);
    }
}
