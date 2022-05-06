package model.strategies;

import model.Position;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * Represents a patrol strategy to move enemies
 */
public class PatrolStrategy implements StrategyEnemy, Serializable  {

    /**
     * Gets enemies strategy type
     * @return strategy type
     */
    public StrategyType getStrategyType() {
        return StrategyType.PATROL;
    }

    /**
     *
     * @param playerPosition - player's position
     * @param enemyPosition - old position
     * @param visibility - visibility radius
     * @param emptyPositions - empty positions around the old positions
     * @return new position
     */
    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility, List<Position> emptyPositions) {
        if (isPlayerVisible(playerPosition, enemyPosition, visibility)) {
            return enemyPosition;
        }
        if (emptyPositions.size() == 0) {
            return enemyPosition;
        }
        Random rand = new Random();
        int p = emptyPositions.size();
        int i = rand.nextInt(p);
        return emptyPositions.get(i);
    }

}
