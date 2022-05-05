package model.strategies;

import model.Position;

import java.io.Serializable;
import java.util.ArrayList;

public class PatrolStrategy implements StrategyEnemy, Serializable  {

    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility, ArrayList<Position> emptyPositions) {
        if (isPlayerVisible(playerPosition, enemyPosition, visibility)) {
            return enemyPosition;
        }

        return null;
    }

}
