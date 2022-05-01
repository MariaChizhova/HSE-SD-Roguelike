package model.enemy;

import model.Position;
import model.strategies.AggressiveStrategy;
import model.strategies.CowardStrategy;
import model.strategies.SimpleStrategy;

/**
 * Red enemy factory
 */
public class CloneEnemyFactory implements EnemyFactory {

    private String name = "clone";
    /**
     * Creates aggressive enemy
     * @param position position of enemy
     * @return aggressive enemy
     */
    @Override
    public Enemy createAggressiveEnemy(Position position) {
        return new Enemy(position, new AggressiveStrategy(), name);
    }

    /**
     * Creates coward enemy
     * @param position position of enemy
     * @return coward enemy
     */
    @Override
    public Enemy createCowardEnemy(Position position) {
        return new Enemy(position, new CowardStrategy(), name);
    }

    /**
     * Creates passive enemy
     * @param position position of enemy
     * @return passive enemy
     */
    @Override
    public Enemy createPassiveEnemy(Position position) {
        return new Enemy(position, new SimpleStrategy(), name);
    }
}
