package model.enemy;

import model.Position;

/**
 * Interface for enemy factory
 */
public interface EnemyFactory {
    /**
     * Creates aggressive enemy
     * @param position position of enemy
     * @return aggressive enemy
     */
    Enemy createAggressiveEnemy(Position position);

    /**
     * Creates coward enemy
     * @param position position of enemy
     * @return coward enemy
     */
    Enemy createCowardEnemy(Position position);

    /**
     * Creates passive enemy
     * @param position position of enemy
     * @return coward enemy
     */
    Enemy createPassiveEnemy(Position position);

    /**
     * Creates patrol enemy
     * @param position position of enemy
     * @return patrol enemy
     */
    Enemy createPatrolEnemy(Position position);

    /**
     * Creates tracker enemy
     * @param position position of enemy
     * @return tracker enemy
     */
    Enemy createTrackerEnemy(Position position);
}
