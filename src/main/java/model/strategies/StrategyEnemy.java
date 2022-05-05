package model.strategies;

import java.util.ArrayList;
import java.util.Random;

import model.Position;

/**
 * Represents an interface for enemy movement strategies
 */
public interface StrategyEnemy {

    /**
     * Makes one enemy move
     * @param playerPosition - player's position
     * @param enemyPosition - old position
     * @param visibility - visibility radius
     * @return new position
     */
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility, ArrayList<Position> emptyPositions);

    /**
     * Checks if player in enemy's visibility radius
     * @param playerPosition - player's position
     * @param enemyPosition - old position
     * @param visibility - visibility radius
     * @return whether the player is visible
     */
    public default boolean isPlayerVisible(Position playerPosition, Position enemyPosition, int visibility) {
        return Math.abs(playerPosition.getX() - enemyPosition.getX()) >= visibility
                || Math.abs(playerPosition.getY() - enemyPosition.getY()) >= visibility;
    }

    /**
     * Gets one of enemies strategies
     * @return new strategy
     */
    public static StrategyEnemy getRandomStrategy() {
        Random rand = new Random();
        switch(rand.nextInt(3)) {
            case 0:
                return new SimpleStrategy();
            case 1:
                return new AggressiveStrategy();
            case 2:
                return new CowardStrategy();
        }
        return new SimpleStrategy();
    }
}
