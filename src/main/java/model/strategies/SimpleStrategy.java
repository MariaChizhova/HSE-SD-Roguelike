package model.strategies;

import model.Position;
import model.strategies.StrategyEnemy;

import java.io.Serializable;

/**
 * Represents a simple strategy to move enemies
 */
public class SimpleStrategy implements StrategyEnemy, Serializable {

    /**
     * The enemy is standing in one place
     * @param playerPosition - player's position
     * @param enemyPosition - old position
     * @return new position
     */
    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition) {
        return enemyPosition;
    }

}
