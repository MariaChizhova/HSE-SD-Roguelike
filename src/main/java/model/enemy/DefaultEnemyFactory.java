package model.enemy;

import model.Position;
import model.strategies.AggressiveStrategy;
import model.strategies.CowardStrategy;
import model.strategies.SimpleStrategy;

/**
 * Default enemy factory
 */
public class DefaultEnemyFactory implements EnemyFactory {

    private String color = "white";
    private final String name = "default";

    /**
     * Creates aggressive enemy
     * @param position position of enemy
     * @return aggressive enemy
     */
    @Override
    public Enemy createAggressiveEnemy(Position position) {
        return new Enemy(position, new AggressiveStrategy(), color);
    }

    /**
     * Creates coward enemy
     * @param position position of enemy
     * @return coward enemy
     */
    @Override
    public Enemy createCowardEnemy(Position position) {
        return new Enemy(position, new CowardStrategy(), color);
    }

    /**
     * Creates passive enemy
     * @param position position of enemy
     * @return passive enemy
     */
    @Override
    public Enemy createPassiveEnemy(Position position) {
        return new Enemy(position, new SimpleStrategy(), color);
    }
}
