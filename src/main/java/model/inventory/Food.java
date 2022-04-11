package model.inventory;

import java.io.Serializable;

/**
 * Represents food itself
 */
public class Food implements Serializable {

    private final int health;

    /**
     * Creating food instance
     * @param health Its health
     */
    public Food(int health) {
        this.health = health;
    }

    /**
     * Getting health of the food
     * @return of the artifact
     */
    public int getHealth() {
        return health;
    }

}
