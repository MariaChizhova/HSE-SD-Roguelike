package ru.hse.roguelike.model.state;

import ru.hse.roguelike.model.enemy.Enemy;
import ru.hse.roguelike.model.strategies.CowardStrategy;
import ru.hse.roguelike.model.strategies.StrategyEnemy;

import java.io.Serializable;

/**
 * Represents panic state of enemy
 */
public class PanicStateEnemy implements StateEnemy, Serializable {
    /**
     * Change to a different state
     */
    @Override
    public void changeState(Enemy enemy) {
        enemy.setState(new OkStateEnemy());
    }

    /**
     * Returns coward strategy
     * @param strategy - default enemy's strategy
     * @return current enemy's strategy
     */
    @Override
    public StrategyEnemy getStrategy(StrategyEnemy strategy) {
        return new CowardStrategy();
    }
}
