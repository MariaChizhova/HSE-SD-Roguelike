package model.strategies;

import java.io.Serializable;
import java.util.List;

import model.Position;

public class TrackerStrategy implements StrategyEnemy, Serializable {

    /**
     * Gets enemies strategy type
     * @return strategy type
     */
    public StrategyType getStrategyType() {
        return StrategyType.TRACKER;
    }

    /**
     * The enemy is attacking in visibility radius
     *
     * @param playerPosition - player's position
     * @param enemyPosition  - old position
     * @param visibility     - enemy's visibility
     * @param emptyPositions - empty positions around the old positions
     * @return new position
     */
    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility, List<Position> emptyPositions) {
        if (playerPosition == null || playerPosition == enemyPosition) {
            return enemyPosition;
        }
        return findPath(playerPosition, enemyPosition, emptyPositions);
    }


}