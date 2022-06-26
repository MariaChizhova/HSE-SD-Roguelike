package ru.hse.roguelike.model;

import java.util.Random;

/**
 * Represents directions of Wall
 */
public enum WallDirection {
    RIGHT,
    LEFT,
    UP,
    DOWN,
    NONE;

    /**
     * Pick a random value of the WallDirection enum.
     *
     * @return a random WallDirection.
     */
    public static WallDirection getRandomDirection() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
