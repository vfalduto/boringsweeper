package com.falduto.vincent.minesweeper.app.object;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by vfalduto on 24/07/2014.
 */
public class TableLayoutGrid extends TableLayout {

    private CaseButton[][] mButtons;

    public TableLayoutGrid(Context context) {
        super(context);
    }

    public TableLayoutGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initFromGrid(Grid grid, OnClickListener listener) {
        mButtons = new CaseButton[grid.getX()][grid.getY()];

        for (int px = 0; px < grid.getX(); px++) {
            TableRow tr = new TableRow(this.getContext());
            for (int py = 0; py < grid.getY(); py++) {
                Coordinate coordinate = new Coordinate(px, py);
                CaseButton caseButton = new CaseButton(this.getContext(), grid.getValue(coordinate), coordinate);
                caseButton.setOnClickListener(listener);
                tr.addView(caseButton);

                mButtons[px][py] = caseButton;
            }
            this.addView(tr);
        }
    }

    public CaseButton getButtonFrom(Coordinate coordinate) {
        return mButtons[coordinate.x][coordinate.y];
    }
}
