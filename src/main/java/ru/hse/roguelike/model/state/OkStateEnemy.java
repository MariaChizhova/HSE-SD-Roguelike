package ru.hse.roguelike.model.state;

import ru.hse.roguelike.model.enemy.Enemy;
import ru.hse.roguelike.model.strategies.StrategyEnemy;

import java.io.Serializable;

/**
 * Represents good state of enemy
 */
public class OkStateEnemy implements StateEnemy, Serializable {
    /**
     * Change to a different state
     */
    @Override
    public void changeState(Enemy enemy) {
        enemy.setState(new PanicStateEnemy());
    }

    /**
     * Returns default enemy's strategy
     * @param strategy - default enemy's strategy
     * @return current enemy's strategy
     */
    @Override
    public StrategyEnemy getStrategy(StrategyEnemy strategy) {
        return strategy;
    }
}
