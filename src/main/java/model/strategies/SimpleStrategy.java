package model.strategies;

import model.Position;
import model.strategies.StrategyEnemy;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a simple strategy to move enemies
 */
public class SimpleStrategy implements StrategyEnemy, Serializable {

    /**
     * The enemy is standing in one place
     * @param playerPosition - player's position
     * @param enemyPosition - old position
     * @param visibility - enemy's visibility
     * @param emptyPositions - empty positions around the old positions
     * @return new position
     */
    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility, ArrayList<Position> emptyPositions) {
        return enemyPosition;
    }

}
