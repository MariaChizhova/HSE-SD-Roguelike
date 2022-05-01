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
    Enemy createAggressiveEnemy(Position position, String name);

    /**
     * Creates coward enemy
     * @param position position of enemy
     * @return coward enemy
     */
    Enemy createCowardEnemy(Position position, String name);

    /**
     * Creates passive enemy
     * @param position position of enemy
     * @return coward enemy
     */
    Enemy createPassiveEnemy(Position position, String name);
}
