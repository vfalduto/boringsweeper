package com.falduto.vincent.minesweeper.app.object;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.falduto.vincent.minesweeper.app.util.ColorRandom;

/**
 * Created by vfalduto on 23/07/2014.
 */
public class CaseButton extends android.widget.Button {

    public static int STATE_HIDE = 0;
    public static int STATE_REVEAL = 1;
    public static int STATE_START = 2;


    private Coordinate mCoordinate;
    private int mState;
    private int mValue;

    public CaseButton(Context context, int value, Coordinate coordinate) {
        super(context);

        mCoordinate = coordinate;
        mState = STATE_HIDE;
        mValue = value;
        hide();
    }

    public Coordinate getCoordinate() {
        return mCoordinate;
    }

    public CaseButton(Context context) {
        super(context);
    }

    public CaseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CaseButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public int getState() {
        return mState;
    }

    public void setState(int mState) {
        this.mState = mState;
        if (this.mState == STATE_REVEAL) {
            this.reveal();
        }
    }

    public void reveal() {
        this.setBackgroundColor(Color.BLUE);
        this.setTextSize(20.0f);
        this.setWidth(90);
        this.setHeight(90);

        if (this.mValue == Grid.TYPE_MINE) {
            this.setText("M");
            this.setBackgroundColor(Color.RED);
        } else if (this.mValue > 0) {
            this.setText("" + this.mValue);
        }
    }

    public void hide() {
        this.setBackgroundColor(Color.GREEN);
        this.setTextSize(20.0f);
        this.setWidth(90);
        this.setHeight(90);
    }
}
