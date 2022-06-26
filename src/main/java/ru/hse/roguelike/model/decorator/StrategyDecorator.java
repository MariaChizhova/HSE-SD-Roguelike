package ru.hse.roguelike.model.decorator;

import ru.hse.roguelike.model.Position;
import ru.hse.roguelike.model.strategies.StrategyEnemy;

import java.io.Serializable;
import java.util.List;

/**
 * Decorator for enemy strategies
 */
public abstract class StrategyDecorator implements StrategyEnemy, Serializable {
    protected StrategyEnemy decorate;

    /**
     * Creates StrategyDecorator instance
     * @param decorate
     */
    public StrategyDecorator(StrategyEnemy decorate) {
        this.decorate = decorate;
    }

    /**
     * Makes one enemy move
     * @param playerPosition - player's position
     * @param enemyPosition - old position
     * @return new position
     */
    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility, List<Position> emptyPositions) {
        return decorate.nextMove(playerPosition, enemyPosition, visibility, emptyPositions);
    }
}
