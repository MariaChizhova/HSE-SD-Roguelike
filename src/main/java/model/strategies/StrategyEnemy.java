package model.strategies;

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
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility);

}
