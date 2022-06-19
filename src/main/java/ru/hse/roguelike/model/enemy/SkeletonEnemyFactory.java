package ru.hse.roguelike.model.enemy;

import ru.hse.roguelike.model.Position;
import ru.hse.roguelike.model.strategies.AggressiveStrategy;
import ru.hse.roguelike.model.strategies.CowardStrategy;
import ru.hse.roguelike.model.strategies.PatrolStrategy;
import ru.hse.roguelike.model.strategies.SimpleStrategy;
import ru.hse.roguelike.model.strategies.TrackerStrategy;

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

    /**
     * Creates tracker enemy
     * @param position position of enemy
     * @return tracker enemy
     */
    @Override
    public Enemy createTrackerEnemy(Position position) {
        return new Enemy(position, new TrackerStrategy(), SKELETON_ENEMY, SKELETONDAMAGE, DEFAULT, SKELETONEXP);
    }
}
