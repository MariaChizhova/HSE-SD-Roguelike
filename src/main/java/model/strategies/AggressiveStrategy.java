package model.strategies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Position;

public class AggressiveStrategy implements StrategyEnemy, Serializable {

    /**
     * The enemy is attacking in visibility radius
     *
     * @param playerPosition - player's position
     * @param enemyPosition  - old position
     * @param visibility     - enemy's visibility
     * @param emptyPositions - empty positions around the old positions
     * @return new position
     */
    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility,
                             ArrayList<Position> emptyPositions) {
        if (isPlayerVisible(playerPosition, enemyPosition, visibility)) {
            return enemyPosition;
        }
        int diffX = playerPosition.getX() - enemyPosition.getX();
        int diffY = playerPosition.getY() - enemyPosition.getY();

        if (Math.abs(diffX) + Math.abs(diffY) == 1) {
            return playerPosition;
        }
        // same X line
        if (diffX == 0) {
            if (diffY >= 0) {
                return tryMoveY(enemyPosition, new Position(enemyPosition.getX(), enemyPosition.getY() + 1), emptyPositions);
            }
            return tryMoveY(enemyPosition, new Position(enemyPosition.getX(), enemyPosition.getY() - 1), emptyPositions);
        }

        // same Y line
        if (diffY == 0) {
            if (diffX >= 0) {
                return tryMoveX(enemyPosition, new Position(enemyPosition.getX() + 1, enemyPosition.getY()), emptyPositions);
            }
            return tryMoveX(enemyPosition, new Position(enemyPosition.getX() - 1, enemyPosition.getY()), emptyPositions);
        }

        if (diffX < diffY) {
            if (diffX >= 0) {
                return tryMoveX(enemyPosition, new Position(enemyPosition.getX() + 1, enemyPosition.getY()), emptyPositions);
            }
            return tryMoveX(enemyPosition, new Position(enemyPosition.getX() - 1, enemyPosition.getY()), emptyPositions);
//            return new Position(diffX >= 0 ? enemyPosition.getX() + 1 : enemyPosition.getX() - 1, enemyPosition.getY());
        }
        if (diffY >= 0) {
            return tryMoveY(enemyPosition, new Position(enemyPosition.getX(), enemyPosition.getY() + 1), emptyPositions);
        }
        return tryMoveY(enemyPosition, new Position(enemyPosition.getX(), enemyPosition.getY() - 1), emptyPositions);
//        return new Position(enemyPosition.getX(), diffY >= 0 ? enemyPosition.getY() + 1 : enemyPosition.getY() - 1);
    }

    private Position tryMoveX(Position enemyPosition, Position newPosition, List<Position> emptyPositions) {
        if (emptyPositions.contains(newPosition)) {
            return newPosition;
        } else {
            return moveByY(enemyPosition, emptyPositions);
        }
    }

    private Position tryMoveY(Position enemyPosition, Position newPosition, List<Position> emptyPositions) {
        if (emptyPositions.contains(newPosition)) {
            return newPosition;
        } else {
            return moveByX(enemyPosition, emptyPositions);
        }
    }

    private Position moveByX(Position position, List<Position> emptyPositions) {
        var newPosition = new Position(position.getX() + 1, position.getY());
        if (emptyPositions.contains(newPosition)) {
            return newPosition;
        }
        return new Position(position.getX() - 1, position.getY());
    }

    private Position moveByY(Position position, List<Position> emptyPositions) {
        var newPosition = new Position(position.getX(), position.getY() + 1);
        if (emptyPositions.contains(newPosition)) {
            return newPosition;
        }
        return new Position(position.getX(), position.getY() - 1);
    }
}
