package model;

import java.util.Random;

public enum CellType {
    EMPTY_CELL,
    ENEMY,
    WALL,
    PLAYER;

    /**
     * Pick a random value of the CellType enum.
     *
     * @return a random CellType.
     */
    public static CellType getRandomCell() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    /**
     * Pick a random value (not player) of the CellType enum.
     *
     * @return a random CellType.
     */
    public static CellType getRandomCellNotPlayer() {
        Random random = new Random();
        return values()[random.nextInt(values().length - 1)];
    }
}
