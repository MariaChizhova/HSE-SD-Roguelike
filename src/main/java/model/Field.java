package model;

public class Field {

    public static final int FIELD_WIDTH = 20;
    public static final int FIELD_HEIGHT = 15;

    private final int width;
    private final int height;
    private Cell[][] field;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Cell[width][height];
        //field[4][4] = new Player(new Position(4, 4));
        //field[4][6] = new Wall();
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

    public void moveEnemy(Position position, Enemy enemy) {
        field[enemy.getPosition().getX()][enemy.getPosition().getY()] = new EmptyCell();
        field[position.getX()][position.getY()] = enemy;
    }


}
