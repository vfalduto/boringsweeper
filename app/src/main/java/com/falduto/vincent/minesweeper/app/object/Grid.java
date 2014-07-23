package com.falduto.vincent.minesweeper.app.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vfalduto on 23/07/2014.
 */
public class Grid {
    public final static int TYPE_MINE = 11;

    private int[][] mBoard;
    private int mTotalMine;
    private int mX;
    private int mY;

    public Grid(int x, int y, int mine) {

        this.mX = x;
        this.mY = y;
        this.mTotalMine = mine;

        generateBoard();
    }

    public int getValue(int x, int y) {
        return mBoard[x][y];
    }

    public boolean isMine(int x, int y) {
        return this.mBoard[x][y] == TYPE_MINE;
    }

    /**
     * Generate grid
     * todo: do a better work
     */
    private void generateBoard() {
        mBoard = new int[this.mX][this.mY];

        generateMines();
        calculateHints();
    }

    private void calculateHints() {
        for (int x = 0; x < this.mX; x++) {
            for (int y = 0; y < this.mY; y++) {
                if (this.isMine(x, y)) {
                    increaseNearHints(x, y);
                }
            }
        }
    }

    private void increaseNearHints(int x, int y) {
        List<Coordinate> coordinates = getNearCoordinates(x, y);
        for (Coordinate Coordinate : coordinates) {
            if (this.mBoard[Coordinate.x][Coordinate.y] != TYPE_MINE) {
                this.mBoard[Coordinate.x][Coordinate.y]++;
            }
        }
    }

    private void generateMines() {
        Random random = new Random();

        for (int i = 0; i < this.mTotalMine; ) {
            int randomX = random.nextInt(this.mX);
            int randomY = random.nextInt(this.mY);

            if (this.validateMineCoordinates(randomX, randomY)) {
                this.mBoard[randomX][randomY] = TYPE_MINE;
                i++;
            }
        }
    }

    private boolean validateMineCoordinates(int x, int y) {
        List<Coordinate> coordinates = getNearCoordinates(x, y);

        int nearMineCount = 0;

        for (Coordinate Coordinate : coordinates) {
            if (mBoard[Coordinate.x][Coordinate.y] == TYPE_MINE) {
                ++nearMineCount;
            }
        }

        //try to avoid 8 mine around one position
        if (nearMineCount == coordinates.size() - 1) {
            return false;
        } else {
            return true;
        }
    }

    private List<Coordinate> getNearCoordinates(int x, int y) {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();

        if (x - 1 > 0) {
            if (y - 1 > 0) {
                coordinates.add(new Coordinate(x - 1, y - 1));
            }

            coordinates.add(new Coordinate(x - 1, y));

            if (y + 1 < this.mY) {
                coordinates.add(new Coordinate(x - 1, y + 1));
            }
        }

        if (x + 1 < this.mX) {
            if (y - 1 > 0) {
                coordinates.add(new Coordinate(x + 1, y - 1));
            }

            coordinates.add(new Coordinate(x + 1, y));

            if (y + 1 < this.mY) {
                coordinates.add(new Coordinate(x + 1, y + 1));
            }
        }

        if (y - 1 > 0) {
            coordinates.add(new Coordinate(x, y - 1));
        }

        if (y + 1 < this.mY) {
            coordinates.add(new Coordinate(x, y + 1));
        }

        return coordinates;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    private class Coordinate {
        public int x;
        public int y;

        public Coordinate(int vx, int vy) {
            this.x = vx;
            this.y = vy;
        }
    }
}
