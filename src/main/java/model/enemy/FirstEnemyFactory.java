package model.enemy;

import model.Position;
import model.strategies.AggressiveStrategy;
import model.strategies.CowardStrategy;
import model.strategies.SimpleStrategy;

public class FirstEnemyFactory implements EnemyFactory {

    @Override
    public Enemy createAggressiveEnemy(Position position) {
        return new Enemy(position, new AggressiveStrategy());
    }

    @Override
    public Enemy createCowardEnemy(Position position) {
        return new Enemy(position, new CowardStrategy());
    }

    @Override
    public Enemy createPassiveEnemy(Position position) {
        return new Enemy(position, new SimpleStrategy());
    }
}
