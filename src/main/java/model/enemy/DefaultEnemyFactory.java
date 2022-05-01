package model.enemy;

import model.Position;
import model.strategies.AggressiveStrategy;
import model.strategies.CowardStrategy;
import model.strategies.SimpleStrategy;

/**
 * Default enemy factory
 */
public class DefaultEnemyFactory implements EnemyFactory {

    /**
     * Creates aggressive enemy
     * @param position position of enemy
     * @return aggressive enemy
     */
    @Override
    public Enemy createAggressiveEnemy(Position position, String name) {
        return new Enemy(position, new AggressiveStrategy(), name);
    }

    /**
     * Creates coward enemy
     * @param position position of enemy
     * @return coward enemy
     */
    @Override
    public Enemy createCowardEnemy(Position position, String name) {
        return new Enemy(position, new CowardStrategy(), name);
    }

    /**
     * Creates passive enemy
     * @param position position of enemy
     * @return passive enemy
     */
    @Override
    public Enemy createPassiveEnemy(Position position, String name) {
        return new Enemy(position, new SimpleStrategy(), name);
    }
}
