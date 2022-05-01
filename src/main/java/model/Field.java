package model;

import java.io.Serializable;
import java.util.List;

import controller.Generation;
import model.enemy.Enemy;


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
        field = new Cell[FIELD_WIDTH][FIELD_HEIGHT];
    }


    /**
     * Creating Field instance
     * @param generation - field generation
     */
    public Field(Generation generation) {
        field = new Cell[FIELD_WIDTH][FIELD_HEIGHT];
        fillField(generation.getGeneration());
    }

    /**
     * Updating generation of field
     * @param generation - field generation
     */
    public void updateGeneration(Generation generation) {
        field = new Cell[FIELD_WIDTH][FIELD_HEIGHT];
        fillField(generation.getGeneration());
    }

    private void fillField(List<GenerationResult> fieldGeneration) {
        for (GenerationResult r : fieldGeneration) {
            field[r.getX()][r.getY()] = r.getCell();
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
        return position.getX() < FIELD_WIDTH && position.getX() >= 0 && position.getY() < FIELD_HEIGHT && position.getY() >= 0;
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

    /**
     * Create an enemy on the field
     * @param position - its position
     * @param enemy - its enemy
     */
    public void addEnemy(Position position, Enemy enemy) {
        field[position.getX()][position.getY()] = enemy;
    }

}
