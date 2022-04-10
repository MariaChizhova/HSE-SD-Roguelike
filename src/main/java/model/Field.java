package model;

import java.util.Random;

import static model.WallDirection.getRandomDirection;

/**
 * Stores information about the field: which objects are in the field grid
 */
public class Field {
    public static final int FIELD_WIDTH = 19;
    public static final int FIELD_HEIGHT = 13;
    public static final int MAX_NUM_OF_ENEMIES = 15;

    private final int width;
    private final int height;
    private final Cell[][] field;
    private final Boolean[][] isFilled;

    /**
     * Creating Field instance
     * @param width - field width
     * @param height - field height
     */
    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Cell[width][height];
        isFilled = new Boolean[width][height];
        for (int y = 0; y < FIELD_HEIGHT; y ++) {
            for (int x = 0; x < FIELD_WIDTH; x ++) {
                isFilled[x][y] = false;
            }
        }
        generateWalls();
        generateEnemies();
        generatePlayer();
    }

    private void generateWalls() {
        for (int y = 1; y < FIELD_HEIGHT; y+=2) {
            for (int x = 1; x < FIELD_WIDTH; x+=2) {
                field[x][y] =  new Wall();
                isFilled[x][y] = true;
                switch (getRandomDirection()) {
                    case UP:
                        setCellValue(x, y - 1, new Wall());
                        break;
                    case DOWN:
                        setCellValue(x, y + 1, new Wall());
                        break;
                    case RIGHT:
                        setCellValue(x + 1, y, new Wall());
                        break;
                    case LEFT:
                        setCellValue(x - 1, y, new Wall());
                        break;
                }
            }
        }
    }

    private void generateEnemies() {
        int cnt = 0;
        Random rand = new Random();

        int numOfEnemies = rand.nextInt(MAX_NUM_OF_ENEMIES);
        while (cnt != numOfEnemies) {
            int enemyXPos = rand.nextInt(FIELD_WIDTH);
            int enemyYPos = rand.nextInt(FIELD_HEIGHT);
            if (!isFilled[enemyXPos][enemyYPos]) {
                setCellValue(enemyXPos, enemyYPos, new Enemy(new Position(enemyXPos, enemyYPos), new SimpleStrategy()));
                cnt++;
            }
        }
    }

    private void generatePlayer() {
        Random rand = new Random();
        while (true) {
            int playerXPos = rand.nextInt(FIELD_WIDTH);
            int playerYPos = rand.nextInt(FIELD_HEIGHT);
            if (!isFilled[playerXPos][playerYPos]) {
                field[playerXPos][playerYPos] = new Player(new Position(playerXPos, playerYPos));
                break;
            }
        }

    }

    private void setCellValue(int x, int y, Cell cell) {
        if (x >= 0 && y >= 0 && x < FIELD_WIDTH && y < FIELD_HEIGHT) {
            field[x][y] = cell;
            isFilled[x][y] = true;
        }
    }


    /**
     * @param position on the field
     * @return the cell of the field
     */
    public Cell getCell(Position position) {
        return field[position.getX()][position.getY()];
    }

    /**
     * Checks whether the position does not go beyond the boundaries of the field
     * @param position on the field
     * @return whether the position is valid
     */
    public boolean isValidPosition(Position position) {
        return position.getX() < width && position.getX() >= 0 && position.getY() < height && position.getY() >= 0;
    }

    /**
     * Creates an empty cell in the player's old place
     * The player is moved to a new cell
     * @param position - the player's old place
     * @param player - its player
     */
    public void movePlayer(Position position, Player player) {
        field[player.getPosition().getX()][player.getPosition().getY()] = new EmptyCell();
        field[position.getX()][position.getY()] = player;
    }

    /**
     * Creates an empty cell in the enemy's old place
     * The enemy is moved to a new cell
     * @param position - the enemy's old place
     * @param enemy - its enemy
     */
    public void moveEnemy(Position position, Enemy enemy) {
        field[enemy.getPosition().getX()][enemy.getPosition().getY()] = new EmptyCell();
        field[position.getX()][position.getY()] = enemy;
    }

    /**
     * Clears the cell - makes it empty
     * @param position - its position
     */
    public void clearCage(Position position) {
        field[position.getX()][position.getY()] = new EmptyCell();
    }


}
