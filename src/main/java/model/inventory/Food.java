package model.inventory;

import model.Position;

public class Food {

    private final Position position;
    private final int health;

    public Food(Position position, int health) {
        this.position = position;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public Position getPosition() {
        return position;
    }

}
