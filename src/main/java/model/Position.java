package model;

import java.io.Serializable;

/**
 * Represents a position on the field
 */
public class Position implements Serializable {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
