package model.enemy;

import model.Position;
import model.strategies.AggressiveStrategy;
import model.strategies.CowardStrategy;
import model.strategies.PatrolStrategy;
import model.strategies.SimpleStrategy;

/**
 * Clone enemy factory
 */
public class CloneEnemyFactory implements EnemyFactory {

    public final static String CLONE_ENEMY = "clone";
    private final static int DEFAULT = 5;
    /**
     * Creates aggressive enemy
     * @param position position of enemy
     * @return aggressive enemy
     */
    @Override
    public Enemy createAggressiveEnemy(Position position) {
        return new Enemy(position, new AggressiveStrategy(), CLONE_ENEMY, DEFAULT, DEFAULT, DEFAULT);
    }

    /**
     * Creates coward enemy
     * @param position position of enemy
     * @return coward enemy
     */
    @Override
    public Enemy createCowardEnemy(Position position) {
        return new Enemy(position, new CowardStrategy(), CLONE_ENEMY, DEFAULT, DEFAULT, DEFAULT);
    }

    /**
     * Creates passive enemy
     * @param position position of enemy
     * @return passive enemy
     */
    @Override
    public Enemy createPassiveEnemy(Position position) {
        return new Enemy(position, new SimpleStrategy(), CLONE_ENEMY, DEFAULT, DEFAULT, DEFAULT);
    }

    /**
     * Creates patrol enemy
     * @param position position of enemy
     * @return patrol enemy
     */
    @Override
    public Enemy createPatrolEnemy(Position position) {
        return new Enemy(position, new PatrolStrategy(), CLONE_ENEMY, DEFAULT, DEFAULT, DEFAULT);
    }
}
