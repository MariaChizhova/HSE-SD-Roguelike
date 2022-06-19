package ru.hse.roguelike.model.state;

import ru.hse.roguelike.model.enemy.Enemy;
import ru.hse.roguelike.model.strategies.StrategyEnemy;

/**
 * Interface for enemy state
 */
public interface StateEnemy {
    /**
     * Change to a different state
     */
    void changeState(Enemy enemy);

    /**
     * Get enemy's strategy depending on the state
     * @param strategy - default enemy's strategy
     * @return current enemy's strategy
     */
    StrategyEnemy getStrategy(StrategyEnemy strategy);
}