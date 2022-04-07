package model;

public class SimpleStrategy implements StrategyEnemy{

    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition) {
        return enemyPosition;
    }

}
