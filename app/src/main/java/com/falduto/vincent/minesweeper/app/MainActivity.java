package com.falduto.vincent.minesweeper.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TableLayout;

import com.falduto.vincent.minesweeper.app.game.GameController;
import com.falduto.vincent.minesweeper.app.util.ColorRandom;
import com.falduto.vincent.minesweeper.app.util.TwoDScrollView;

public class MainActivity extends Activity {

    protected TwoDScrollView mBoard;
    protected TableLayout mGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSystemUiFullscreenMode();
        initBoardAndSetPosition();
        initGrid();
        setRandomColorBackground();

        GameController gameController = new GameController(mGrid);
        gameController.setGameControllerListener(new GameController.GameControllerListener() {

            @Override
            public void onGameStateChange(int State) {
                //todo display win/lose state
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initSystemUiFullscreenMode();
    }

    public void setRandomColorBackground() {
        findViewById(R.id.background).setBackgroundColor(ColorRandom.getInstance().generateRandomColor());
    }

    public void initGrid() {
        mGrid = (TableLayout) findViewById(R.id.grid);
        mGrid.setPadding(1, 1, 1, 1);
    }

    public void initBoardAndSetPosition() {
        mBoard = (TwoDScrollView) findViewById(R.id.board);
        ViewTreeObserver viewTreeObserver = mBoard.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mBoard.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    mBoard.center();
                }
            });
        }
    }

    public void initSystemUiFullscreenMode() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }
}
