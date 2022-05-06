package model.enemy;

import model.Position;
import model.strategies.AggressiveStrategy;
import model.strategies.CowardStrategy;
import model.strategies.PatrolStrategy;
import model.strategies.SimpleStrategy;
import model.strategies.TrackerStrategy;

/**
 * Dragon enemy factory
 */
public class DragonEnemyFactory implements EnemyFactory {

    public final static String DRAGON_ENEMY = "dragon";
    private final static int DEFAULT = 5;
    private final static int DRAGONARMOR = 6;
    private final static int DRAGONEXP = 6;

    /**
     * Creates aggressive enemy
     * @param position position of enemy
     * @return aggressive enemy
     */
    @Override
    public Enemy createAggressiveEnemy(Position position) {
        return new Enemy(position, new AggressiveStrategy(), DRAGON_ENEMY, DEFAULT, DRAGONARMOR, DRAGONEXP);
    }

    /**
     * Creates coward enemy
     * @param position position of enemy
     * @return coward enemy
     */
    @Override
    public Enemy createCowardEnemy(Position position) {
        return new Enemy(position, new CowardStrategy(), DRAGON_ENEMY, DEFAULT, DRAGONARMOR, DRAGONEXP);
    }

    /**
     * Creates passive enemy
     * @param position position of enemy
     * @return passive enemy
     */
    @Override
    public Enemy createPassiveEnemy(Position position) {
        return new Enemy(position, new SimpleStrategy(), DRAGON_ENEMY, DEFAULT, DRAGONARMOR, DRAGONEXP);
    }

    /**
     * Creates patrol enemy
     * @param position position of enemy
     * @return patrol enemy
     */
    @Override
    public Enemy createPatrolEnemy(Position position) {
        return new Enemy(position, new PatrolStrategy(), DRAGON_ENEMY, DEFAULT, DRAGONARMOR, DRAGONEXP);
    }

    /**
     * Creates tracker enemy
     * @param position position of enemy
     * @return tracker enemy
     */
    @Override
    public Enemy createTrackerEnemy(Position position) {
        return new Enemy(position, new TrackerStrategy(), DRAGON_ENEMY, DEFAULT, DRAGONARMOR, DRAGONEXP);
    }
}
