package com.falduto.vincent.minesweeper.app.game;

import com.falduto.vincent.minesweeper.app.object.CaseButton;
import com.falduto.vincent.minesweeper.app.object.Coordinate;
import com.falduto.vincent.minesweeper.app.object.Grid;
import com.falduto.vincent.minesweeper.app.object.TableLayoutGrid;

/**
 * Created by vfalduto on 21/07/2014.
 */
public class GameController {
    public final static int STATE_WIN = 1;
    public final static int STATE_LOSE = 2;
    public final static int STATE_PLAY = 3;
    private int mState;

    private GameControllerListener mListener = null;

    private Grid mGrid = null;

    public GameController() {
        this.setState(STATE_PLAY);
    }

    public void start() {
        this.mGrid = new Grid(15, 20, 25);

        if (mListener != null) {
            mListener.onGridCreated(this.mGrid);
        }
    }

    public interface GameControllerListener {
        public void onGridCreated(Grid grid);

        public void onGameStateChange(int state);
    }

    public void setGameControllerListener(GameControllerListener listener) {
        mListener = listener;
    }

    public void reveal(CaseButton button, TableLayoutGrid tableLayoutGrid) {
        if (button.getState() != CaseButton.STATE_REVEAL) {
            button.setState(CaseButton.STATE_REVEAL);

            if (this.mGrid.isMine(button.getCoordinate())) {
                this.setState(STATE_LOSE);
            }

            if (this.mGrid.getValue(button.getCoordinate()) == mGrid.TYPE_EMPTY) {
                this.revealNear(button, tableLayoutGrid);
            }
        }
    }

    private void revealNear(CaseButton button, TableLayoutGrid tableLayoutGrid) {
        for (Coordinate coordinate : this.mGrid.getNearCoordinates(button.getCoordinate())) {
            CaseButton nearButton = tableLayoutGrid.getButtonFrom(coordinate);

            if ((!this.mGrid.isMine(nearButton.getCoordinate())) && nearButton.getState() != CaseButton.STATE_REVEAL) {
                nearButton.setState(CaseButton.STATE_REVEAL);

                if (this.mGrid.getValue(nearButton.getCoordinate()) == mGrid.TYPE_EMPTY) {
                    this.revealNear(nearButton, tableLayoutGrid);
                }
            }
        }
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

        if (mListener != null) {
            mListener.onGameStateChange(this.mState);
        }
    }
}
