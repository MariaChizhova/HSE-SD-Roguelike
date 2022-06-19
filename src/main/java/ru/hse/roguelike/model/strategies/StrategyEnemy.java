package ru.hse.roguelike.model.strategies;

import java.util.List;
import java.util.Random;

import ru.hse.roguelike.model.Position;

/**
 * Represents an interface for enemy movement strategies
 */
public interface StrategyEnemy {
    /**
     * Gets enemies strategy type
     * @return strategy type
     */
    public StrategyType getStrategyType();

    /**
     * Makes one enemy move
     * @param playerPosition - player's position
     * @param enemyPosition - old position
     * @param visibility - visibility radius
     * @param emptyPositions - empty positions around the old positions
     * @return new position
     */
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility, List<Position> emptyPositions);

    /**
     * Checks if player in enemy's visibility radius
     * @param playerPosition - player's position
     * @param enemyPosition - old position
     * @param visibility - visibility radius
     * @return whether the player is visible
     */
    public default boolean isPlayerVisible(Position playerPosition, Position enemyPosition, int visibility) {
        return Math.abs(playerPosition.getX() - enemyPosition.getX()) >= visibility
                || Math.abs(playerPosition.getY() - enemyPosition.getY()) >= visibility;
    }

    public default Position findPath(Position playerPosition, Position enemyPosition, List<Position> emptyPositions) {
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
        }
        if (diffY >= 0) {
            return tryMoveY(enemyPosition, new Position(enemyPosition.getX(), enemyPosition.getY() + 1), emptyPositions);
        }
        return tryMoveY(enemyPosition, new Position(enemyPosition.getX(), enemyPosition.getY() - 1), emptyPositions);
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

    /**
     * Gets one of enemies strategies
     * @return new strategy
     */
    public static StrategyEnemy getRandomStrategy() {
        Random rand = new Random();
        switch(rand.nextInt(StrategyType.values().length)) {
            case 0:
                return new SimpleStrategy();
            case 1:
                return new AggressiveStrategy();
            case 2:
                return new CowardStrategy();
            case 3:
                return new PatrolStrategy();
            case 4:
                return new SearchingStrategy();
        }
        return new SimpleStrategy();
    }
}
