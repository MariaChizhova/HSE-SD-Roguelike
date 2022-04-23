package model.enemy;

import model.Position;

public interface EnemyFactory {
    Enemy createAggressiveEnemy(Position position);
    Enemy createCowardEnemy(Position position);
    Enemy createPassiveEnemy(Position position);
}
