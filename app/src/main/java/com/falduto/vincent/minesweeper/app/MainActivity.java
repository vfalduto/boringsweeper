package com.falduto.vincent.minesweeper.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.falduto.vincent.minesweeper.app.game.GameController;
import com.falduto.vincent.minesweeper.app.object.CaseButton;
import com.falduto.vincent.minesweeper.app.object.Coordinate;
import com.falduto.vincent.minesweeper.app.object.Grid;
import com.falduto.vincent.minesweeper.app.object.TableLayoutGrid;
import com.falduto.vincent.minesweeper.app.util.ColorRandom;
import com.falduto.vincent.minesweeper.app.util.TwoDScrollView;

public class MainActivity extends Activity implements View.OnClickListener {

    private TwoDScrollView mScrollViewContainer;
    private TableLayoutGrid mTableLayout;
    private GameController gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSystemUiFullscreenMode();

        //visual objects
        initScrollViewContainer();
        mTableLayout = (TableLayoutGrid) findViewById(R.id.grid);
        setRandomColorBackground();

        //game object
        gameController = new GameController();
        gameController.setGameControllerListener(new GameController.GameControllerListener() {

            @Override
            public void onGridCreated(Grid grid) {
                mTableLayout.initFromGrid(grid, MainActivity.this);
            }

            @Override
            public void onGameStateChange(int state) {
                if(state == GameController.STATE_PLAY) {
                    return;
                }

                FrameLayout layout = (FrameLayout) findViewById(R.id.slide);
                layout.setVisibility(View.VISIBLE);
                TextView text = (TextView) findViewById(R.id.result);
                text.setText((state == GameController.STATE_LOSE ? "Lose" : "Win"));
            }
        });
        gameController.start();

    }

    @Override
    public void onClick(View v) {
        if (v instanceof CaseButton) {
            this.gameController.reveal((CaseButton) v, this.mTableLayout);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        initSystemUiFullscreenMode();
    }

    public void setRandomColorBackground() {
        findViewById(R.id.background).setBackgroundColor(ColorRandom.getInstance().generateRandomColor());
    }

    public void initScrollViewContainer() {
        mScrollViewContainer = (TwoDScrollView) findViewById(R.id.board);

        ViewTreeObserver viewTreeObserver = mScrollViewContainer.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mScrollViewContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    mScrollViewContainer.center();
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
