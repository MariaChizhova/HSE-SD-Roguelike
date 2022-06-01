package model;

import java.io.Serializable;
import java.util.List;

import controller.MapGenerator;


/**
 * Stores information about the field: which objects are in the field grid
 */
public class Field implements Serializable {
    public static final int FIELD_WIDTH = 19;
    public static final int FIELD_HEIGHT = 13;

    private Cell[][] field;

    /**
     * Creating Field instance
     */
    public Field() {
        createField();
    }

    private void createField() {
        field = new Cell[FIELD_WIDTH][FIELD_HEIGHT];
    }

    /**
     * Creating Field instance
     * @param mapGenerator - field generation
     */
    public Field(MapGenerator mapGenerator) {
        createField();
        fillField(mapGenerator.getGeneration());
    }

    /**
     * Updating generation of field
     * @param mapGenerator - field generation
     */
    public void updateGeneration(MapGenerator mapGenerator) {
        createField();
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
     * Checks whether the position does not go beyond the boundaries of the field
     * @param position on the field
     * @return whether the position is valid
     */
    public boolean isInsideBounds(Position position) {
        return position.getX() < FIELD_WIDTH && position.getX() >= 0 && position.getY() < FIELD_HEIGHT && position.getY() >= 0;
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

}
