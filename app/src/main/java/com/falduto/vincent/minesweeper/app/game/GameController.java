package com.falduto.vincent.minesweeper.app.game;

import android.widget.TableLayout;

import com.falduto.vincent.minesweeper.app.object.Grid;

/**
 * Created by vfalduto on 21/07/2014.
 */
public class GameController {
    public final static int STATE_WIN = 1;
    public final static int STATE_LOSE = 2;
    public final static int STATE_PLAY = 3;
    private int mState;

    private GameControllerListener mListerner = null;

    private Grid mGrid = null;

    public GameController(TableLayout view) {
        this.setState(STATE_PLAY);
        this.mGrid = new Grid(view, 15,20, 99);
    }

    public interface GameControllerListener {
        public void onGameStateChange(int State);
    }

    public void setGameControllerListener(GameControllerListener listener) {
        mListerner = listener;
    }

    /*********************************************************************
     * Helpers
     *********************************************************************/

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
