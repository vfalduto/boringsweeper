package com.falduto.vincent.minesweeper.app.util;

import java.util.Random;

/**
 * Created by vfalduto on 23/07/2014.
 */
public class ShuffleArray {

    // Implementing Fisher–Yates shuffle
    public static void FisherYatesShuffle(int[] ar)
    {
        Random random = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = random.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
