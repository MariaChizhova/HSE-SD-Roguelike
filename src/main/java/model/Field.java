package model;

public class Field {

    private int width;
    private int height;
    private Cell[][] field;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Cell[width][height];
    }

    public Cell getCell(Position position) {
        return field[position.getX()][position.getY()];
    }

    public boolean isValidPosition(Position position) {
        return position.getX() < width && position.getX() >= 0 && position.getY() < height && position.getY() >= 0;
    }

    public void movePlayer(Position position, Player player) {
        field[player.getPosition().getX()][player.getPosition().getY()] = new EmptyCell();
        field[position.getX()][position.getY()] = player;
    }


}
