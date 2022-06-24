package model.strategies;

import java.io.Serializable;

import model.Position;

public class CowardStrategy implements StrategyEnemy, Serializable {

    /**
     * The enemy is running away from player in visibility radius
     * @param playerPosition - player's position
     * @param enemyPosition - old position
     * @param visibility - enemy's visibility
     * @return new position
     */
    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility) {
        if (isPlayerUnvisible(playerPosition, enemyPosition, visibility)) {
            return enemyPosition;
        }
        int diffX = playerPosition.getX() - enemyPosition.getX();
        int diffY = playerPosition.getY() - enemyPosition.getY();

        // same X line
        if (diffX == 0) {
            return new Position(enemyPosition.getX(), diffY >= 0 ? enemyPosition.getY() - 1 : enemyPosition.getY() + 1);
        }

        // same Y line
        if (diffY == 0) {
            return new Position(diffX >= 0 ? enemyPosition.getX() - 1 : enemyPosition.getX() + 1, enemyPosition.getY());
        }

        if (diffX < diffY) {
            return new Position(diffX >= 0 ? enemyPosition.getX() - 1 : enemyPosition.getX() + 1, enemyPosition.getY());
        }
        return new Position(enemyPosition.getX(), diffY >= 0 ? enemyPosition.getY() - 1 : enemyPosition.getY() + 1);
    }
}
