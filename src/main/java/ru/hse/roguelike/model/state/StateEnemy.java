package ru.hse.roguelike.model.state;

import ru.hse.roguelike.model.decorator.StrategyDecorator;
import ru.hse.roguelike.model.enemy.Enemy;

/**
 * Interface for enemy state
 */
public interface StateEnemy {
    /**
     * Change to a different state
     */
    void changeState(Enemy enemy);

    /**
     * Get enemy's strategy in decorator depending on the state
     * @param decorator - default enemy's decorator
     * @return current enemy's decorator
     */
    StrategyDecorator getStrategy(StrategyDecorator decorator);
}
