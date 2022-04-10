package model;

import java.util.Random;

import static model.CellType.getRandomCellNotPlayer;

public class Field {

    public static final int FIELD_WIDTH = 20;
    public static final int FIELD_HEIGHT = 15;

    private final int width;
    private final int height;
    private final Cell[][] field;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Cell[width][height];
        for (int y = 0; y < FIELD_HEIGHT; y++) {
            for (int x = 0; x < FIELD_WIDTH; x++) {
                field[x][y] = generateCell(x, y);
            }
        }
        Random rand = new Random();
        int playerXPos = rand.nextInt(FIELD_WIDTH);
        int playerYPos = rand.nextInt(FIELD_WIDTH);
        field[playerXPos][playerYPos] = new Player(new Position(playerXPos, playerYPos));
    }

    private Cell generateCell(int x, int y) {
        switch (getRandomCellNotPlayer()) {
            case EMPTY_CELL -> {
                return new EmptyCell();
            }
            case WALL -> {
                return new Wall();
            }
            case ENEMY -> {
                return new Enemy(new Position(x, y), new SimpleStrategy());
            }
        }
        throw new IllegalStateException(String.format("Failed to generate cell x: %d y: %d", x, y));
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
