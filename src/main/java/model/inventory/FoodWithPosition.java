package model.inventory;

import model.Cell;
import model.Position;

/**
 * Represents food itself
 */
public class FoodWithPosition implements Cell {

    private final Position position;
    private final Food food;

    /**
     * Creating FoodWithPosition instance
     * @param position Its position
     * @param food Food itself
     */
    public FoodWithPosition(Position position, Food food) {
        this.position = position;
        this.food = food;
    }

    /**
     * Getting food
     * @return food
     */
    public Food getFood() {
        return food;
    }

    /**
     * Getting position of the food
     * @return position of the food
     */
    public Position getPosition() {
        return position;
    }

}
