package model.strategies;

import java.io.Serializable;
import java.util.List;

import model.Position;

public class AggressiveStrategy implements StrategyEnemy, Serializable {

    /**
     * Gets enemies strategy type
     * @return strategy type
     */
    public StrategyType getStrategyType() {
        return StrategyType.AGGRESSIVE;
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
        if (isPlayerVisible(playerPosition, enemyPosition, visibility)) {
            return enemyPosition;
        }

        return findPath(playerPosition, enemyPosition, emptyPositions);
    }

}
