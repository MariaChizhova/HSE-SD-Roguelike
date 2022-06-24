package model;

import java.io.Serializable;
import java.util.List;

import controller.MapGenerator;
import model.enemy.Enemy;


/**
 * Stores information about the field: which objects are in the field grid
 */
public class Field implements Serializable {
    private Cell[][] field;
    private int width;
    private int height;

    /**
     * Creating Field instance
     */
    public Field(int width, int height) {
        createField(width, height);
    }

    private void createField(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Cell[width][height];
    }

    /**
     * Creating Field instance
     * @param mapGenerator - field generation
     */
    public Field(MapGenerator mapGenerator) {
        createField(mapGenerator.getWidth(), mapGenerator.getHeight());
        fillField(mapGenerator.getGeneration());
    }

    /**
     * Updating generation of field
     * @param mapGenerator - field generation
     */
    public void updateGeneration(MapGenerator mapGenerator) {
        this.width = mapGenerator.getWidth();
        this.height = mapGenerator.getHeight();
        field = new Cell[width][height];
        fillField(mapGenerator.getGeneration());
    }

    private void fillField(List<GeneratedMap> fieldGeneration) {
        for (GeneratedMap r : fieldGeneration) {
            field[r.x][r.y] = r.cell;
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
     * @param x - x on the field
     * @param y - y on the field
     * @return the cell of the field
     */
    public Cell getCell(int x, int y) {
        return field[x][y];
    }

    /**
     * Checks whether the position does not go beyond the boundaries of the field
     * @param position on the field
     * @return whether the position is valid
     */
    public boolean isInsideBounds(Position position) {
        return position.getX() < width && position.getX() >= 0 && position.getY() < height && position.getY() >= 0;
    }

    /**
     * Checks whether the position does not go beyond the boundaries of the field
     * @param x - x on the field
     * @param y - y on the field
     * @return whether the position is valid
     */
    public boolean isInsideBounds(int x, int y) {
        return x < width && x >= 0 && y < height && y >= 0;
    }

    /**
     * Creates an empty cell in the player's old place
     * The player is moved to a new cell
     * @param newPlayerPosition - the player's new place
     * @param player - its player
     */
    public void movePlayer(Position newPlayerPosition, Player player) {
        field[player.getPosition().getX()][player.getPosition().getY()] = new EmptyCell();
        field[newPlayerPosition.getX()][newPlayerPosition.getY()] = player;
        player.move(newPlayerPosition);
    }

    /**
     * Creates an empty cell in the enemy's old place
     * The enemy is moved to a new cell
     * @param newEnemyPosition - the enemy's new place
     * @param enemy - its enemy
     */
    public void moveEnemy(Position newEnemyPosition, Enemy enemy) {
        field[enemy.getPosition().getX()][enemy.getPosition().getY()] = new EmptyCell();
        field[newEnemyPosition.getX()][newEnemyPosition.getY()] = enemy;
    }

    /**
     * Clears the cell - makes it empty
     * @param position - its position
     */
    public void clearCage(Position position) {
        field[position.getX()][position.getY()] = new EmptyCell();
    }

    /**
     * Create an enemy on the field
     * @param position - its position
     * @param enemy - its enemy
     */
    public void addEnemy(Position position, Enemy enemy) {
        field[position.getX()][position.getY()] = enemy;
    }

    /**
     * Getting width of map
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getting width of map
     *
     * @return width
     */
    public int getHeight() {
        return height;
    }
}
