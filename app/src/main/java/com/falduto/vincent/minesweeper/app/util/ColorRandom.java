package com.falduto.vincent.minesweeper.app.util;


import android.graphics.Color;

import java.util.Random;

/**
 * Created by vfalduto on 21/07/2014.
 */
public class ColorRandom {

    private static ColorRandom instance = null;

    protected ColorRandom() {
        // Exists only to defeat instantiation.
    }

    public static ColorRandom getInstance() {
        if (instance == null) {
            instance = new ColorRandom();
        }
        return instance;
    }

    public int generateRandomColor() {
        Random random = new Random();
        int red = random.nextInt(128) + 127;
        int green = random.nextInt(128) + 127;
        int blue = random.nextInt(128) + 127;

        return Color.argb(255, red, green, blue);
    }
}
