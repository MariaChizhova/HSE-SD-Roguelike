package model.state;

import model.enemy.Enemy;
import model.strategies.CowardStrategy;
import model.strategies.StrategyEnemy;

/**
 * Represents panic state of enemy
 */
public class PanicStateEnemy implements StateEnemy{
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
