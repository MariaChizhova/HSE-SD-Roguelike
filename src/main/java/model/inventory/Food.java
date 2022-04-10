package model.inventory;

import model.Cell;
import model.Position;

/**
 * Represents food itself
 */
public class Food implements Cell {

    private final Position position;
    private final int health;

    /**
     * Creating food instance
     * @param position Its position
     * @param health Its health
     */
    public Food(Position position, int health) {
        this.position = position;
        this.health = health;
    }

    /**
     * Getting health of the food
     * @return of the artifact
     */
    public int getHealth() {
        return health;
    }

    /**
     * Getting position of the food
     * @return position of the food
     */
    public Position getPosition() {
        return position;
    }

}
