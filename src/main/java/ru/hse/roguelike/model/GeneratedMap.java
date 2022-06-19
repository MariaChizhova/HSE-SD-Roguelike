package ru.hse.roguelike.model;

/**
 * Stores information about every cell of generated map
 */
public class GeneratedMap {
    final int x;
    final int y;
    final Cell cell;

    public GeneratedMap(int x, int y, Cell cell) {
        this.x = x;
        this.y = y;
        this.cell = cell;
    }
}
