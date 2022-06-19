package ru.hse.roguelike.model.strategies;

import ru.hse.roguelike.model.Position;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a simple strategy to move enemies
 */
public class SimpleStrategy implements StrategyEnemy, Serializable {

    /**
     * Gets enemies strategy type
     * @return strategy type
     */
    public StrategyType getStrategyType() {
        return StrategyType.SIMPLE;
    }

    /**
     * The enemy is standing in one place
     * @param playerPosition - player's position
     * @param enemyPosition - old position
     * @param visibility - enemy's visibility
     * @param emptyPositions - empty positions around the old positions
     * @return new position
     */
    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility, List<Position> emptyPositions) {
        return enemyPosition;
    }

}
