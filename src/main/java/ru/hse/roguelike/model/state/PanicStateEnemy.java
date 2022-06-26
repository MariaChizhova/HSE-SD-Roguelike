package ru.hse.roguelike.model.state;

import ru.hse.roguelike.model.decorator.ConfuseStrategyDecorator;
import ru.hse.roguelike.model.decorator.StrategyDecorator;
import ru.hse.roguelike.model.enemy.Enemy;
import ru.hse.roguelike.model.strategies.CowardStrategy;

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
     * Returns coward strategy in decorator
     * @param decorator - default enemy's decorator
     * @return current enemy's decorator
     */
    @Override
    public StrategyDecorator getStrategy(StrategyDecorator decorator) {
        return new ConfuseStrategyDecorator(new CowardStrategy(), 0);
    }
}
