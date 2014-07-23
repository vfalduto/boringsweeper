package com.falduto.vincent.minesweeper.app.game;

import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.falduto.vincent.minesweeper.app.object.Button;
import com.falduto.vincent.minesweeper.app.object.Coordinate;
import com.falduto.vincent.minesweeper.app.object.Grid;
import com.falduto.vincent.minesweeper.app.util.ColorRandom;

/**
 * Created by vfalduto on 21/07/2014.
 */
public class GameController implements View.OnClickListener {
    public final static int STATE_WIN = 1;
    public final static int STATE_LOSE = 2;
    public final static int STATE_PLAY = 3;
    private int mState;

    private GameControllerListener mListerner = null;

    private Grid mGrid = null;

    public GameController(TableLayout view) {
        this.setState(STATE_PLAY);

        this.mGrid = new Grid(15, 20, 99);

        //init view
        initBoard(view);
    }

     public interface GameControllerListener {
        public void onGameStateChange(int State);
    }

    public void setGameControllerListener(GameControllerListener listener) {
        mListerner = listener;
    }

    //todo should remove style from here
    public void initBoard(TableLayout view) {
        for (int px = 0; px < this.mGrid.getX(); px++) {
            TableRow tr = new TableRow(view.getContext());
            for (int py = 0; py < this.mGrid.getY(); py++) {
                Button button = new Button(view.getContext(), new Coordinate(px, py));

                button.setBackgroundColor(ColorRandom.getInstance().generateRandomColor());
                if (this.mGrid.isMine(px, py)) {
                    button.setText("M");
                    button.setBackgroundColor(1);
                } else if (this.mGrid.getValue(px, py) > 0) {
                    button.setText("" + this.mGrid.getValue(px, py));
                }

                button.setOnClickListener(this);

                button.setTextSize(20.0f);
                tr.addView(button, 90, 90);
            }
            view.addView(tr);
        }
    }

    @Override
    public void onClick(View v) {
        //todo game here
        Button button = (Button)v;
        CharSequence text = button.getCoordinate().x + " - " + button.getCoordinate().y;
        Toast.makeText(v.getContext(), text, Toast.LENGTH_SHORT);
    }


    /**
     * ******************************************************************
     * Helpers
     * *******************************************************************
     */

    protected void lose() {
        this.setState(STATE_LOSE);
    }

    protected void win() {
        this.setState(STATE_WIN);
    }

    private void setState(int state) {
        mState = state;

        if (mListerner != null) {
            mListerner.onGameStateChange(this.mState);
        }
    }


}
