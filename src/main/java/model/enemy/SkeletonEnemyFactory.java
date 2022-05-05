package model.enemy;

import model.Position;
import model.strategies.AggressiveStrategy;
import model.strategies.CowardStrategy;
import model.strategies.PatrolStrategy;
import model.strategies.SimpleStrategy;

/**
 * Skeleton enemy factory
 */
public class SkeletonEnemyFactory implements EnemyFactory {

    public final static String SKELETON_ENEMY = "skeleton";
    private final static int DEFAULT = 5;
    private final static int SKELETONDAMAGE = 6;
    private final static int SKELETONEXP = 6;

    /**
     * Creates aggressive enemy
     * @param position position of enemy
     * @return aggressive enemy
     */
    @Override
    public Enemy createAggressiveEnemy(Position position) {
        return new Enemy(position, new AggressiveStrategy(), SKELETON_ENEMY, SKELETONDAMAGE, DEFAULT, SKELETONEXP);
    }

    /**
     * Creates coward enemy
     * @param position position of enemy
     * @return coward enemy
     */
    @Override
    public Enemy createCowardEnemy(Position position) {
        return new Enemy(position, new CowardStrategy(), SKELETON_ENEMY, SKELETONDAMAGE, DEFAULT, SKELETONEXP);
    }

    /**
     * Creates passive enemy
     * @param position position of enemy
     * @return passive enemy
     */
    @Override
    public Enemy createPassiveEnemy(Position position) {
        return new Enemy(position, new SimpleStrategy(), SKELETON_ENEMY, SKELETONDAMAGE, DEFAULT, SKELETONEXP);
    }

    /**
     * Creates patrol enemy
     * @param position position of enemy
     * @return patrol enemy
     */
    @Override
    public Enemy createPatrolEnemy(Position position) {
        return new Enemy(position, new PatrolStrategy(), SKELETON_ENEMY, SKELETONDAMAGE, DEFAULT, SKELETONEXP);
    }
}
