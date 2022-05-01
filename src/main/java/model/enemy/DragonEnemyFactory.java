package model.enemy;

import model.Position;
import model.strategies.AggressiveStrategy;
import model.strategies.CowardStrategy;
import model.strategies.SimpleStrategy;

public class DragonEnemyFactory implements EnemyFactory {

    private final String name = "dragon";
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
        return new Enemy(position, new AggressiveStrategy(), name, DEFAULT, DRAGONARMOR, DRAGONEXP);
    }

    /**
     * Creates coward enemy
     * @param position position of enemy
     * @return coward enemy
     */
    @Override
    public Enemy createCowardEnemy(Position position) {
        return new Enemy(position, new CowardStrategy(), name, DEFAULT, DRAGONARMOR, DRAGONEXP);
    }

    /**
     * Creates passive enemy
     * @param position position of enemy
     * @return passive enemy
     */
    @Override
    public Enemy createPassiveEnemy(Position position) {
        return new Enemy(position, new SimpleStrategy(), name, DEFAULT, DRAGONARMOR, DRAGONEXP);
    }
}
