package model;

public class GenerationResult {
    private final int x;
    private final int y;
    private final Cell cell;

    public GenerationResult(int x, int y, Cell cell) {
        this.x = x;
        this.y = y;
        this.cell = cell;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cell getCell() {
        return cell;
    }
}
