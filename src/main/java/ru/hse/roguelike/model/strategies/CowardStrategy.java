package ru.hse.roguelike.model.strategies;

import java.io.Serializable;
import java.util.List;

import ru.hse.roguelike.model.Position;

/**
 * Represents coward enemy strategy
 */
public class CowardStrategy implements StrategyEnemy, Serializable {

    /**
     * Gets enemies strategy type
     * @return strategy type
     */
    public StrategyType getStrategyType() {
        return StrategyType.COWARD;
    }

    /**
     * The enemy is running away from player in visibility radius
     * @param playerPosition - player's position
     * @param enemyPosition - old position
     * @param visibility - enemy's visibility
     * @param emptyPositions - empty positions around the old positions
     * @return new position
     */
    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility, List<Position> emptyPositions) {
        if (isPlayerInvisible(playerPosition, enemyPosition, visibility)) {
            return enemyPosition;
        }
        int diffX = playerPosition.getX() - enemyPosition.getX();
        int diffY = playerPosition.getY() - enemyPosition.getY();

        // same X line
        if (diffX == 0) {
            return new Position(enemyPosition.getX(), diffY >= 0 ? enemyPosition.getY() - 1 : enemyPosition.getY() + 1);
        }

        // same Y line
        if (diffY == 0) {
            return new Position(diffX >= 0 ? enemyPosition.getX() - 1 : enemyPosition.getX() + 1, enemyPosition.getY());
        }

        if (diffX < diffY) {
            return new Position(diffX >= 0 ? enemyPosition.getX() - 1 : enemyPosition.getX() + 1, enemyPosition.getY());
        }
        return new Position(enemyPosition.getX(), diffY >= 0 ? enemyPosition.getY() - 1 : enemyPosition.getY() + 1);
    }
}
