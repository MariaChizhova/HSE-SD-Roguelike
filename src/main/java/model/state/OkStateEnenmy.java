package model.state;

import model.enemy.Enemy;
import model.strategies.StrategyEnemy;

public class OkStateEnenmy implements StateEnemy{
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
