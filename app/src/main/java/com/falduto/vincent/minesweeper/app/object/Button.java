package com.falduto.vincent.minesweeper.app.object;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by vfalduto on 23/07/2014.
 */
public class Button extends android.widget.Button {

    public static int STATE_HIDE = 0;
    public static int STATE_REVEAL = 1;
    public static int STATE_START = 2;


    private Coordinate mCoordinate;
    private int mState;

    public Button(Context context, Coordinate coordinate) {
        super(context);

        mCoordinate = coordinate;
        mState = STATE_HIDE;
    }

    public Button(Context context) {
        super(context);
    }

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Button(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
