package model.strategies;

import model.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchingStrategy implements StrategyEnemy, Serializable {

    /**
     * Gets enemies strategy type
     * @return strategy type
     */
    @Override
    public StrategyType getStrategyType() {
        return StrategyType.SEARCHING;
    }

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
    public Position nextMove(Position playerPosition, Position enemyPosition,
                             int visibility, List<Position> emptyPositions) {
        int diffX = playerPosition.getX() - enemyPosition.getX();
        int diffY = playerPosition.getY() - enemyPosition.getY();

        if (Math.abs(diffX) > visibility && Math.abs(diffY) > visibility) {
            return findPath(playerPosition, enemyPosition, emptyPositions);
        }

        List<Position> possiblePositions = new ArrayList<>();
        int[] changeX = {0, 1, 0, -1};
        int[] changeY = {1, 0, -1, 0};

        for (int i = 0; i < changeX.length; i++) {
            var curPosition = new Position(enemyPosition.getX() + changeX[i], enemyPosition.getY() + changeY[i]);
            if (emptyPositions.contains(curPosition) &&
                    playerPosition.getX() - visibility <= curPosition.getX() &&
                    curPosition.getX() <= playerPosition.getX() + visibility &&
                    playerPosition.getY() - visibility <= curPosition.getY() &&
                    curPosition.getY() <= playerPosition.getY() + visibility) {
                possiblePositions.add(curPosition);
            }
        }

        if (possiblePositions.isEmpty()) {
            return findPath(playerPosition, enemyPosition, emptyPositions);
        }

        Random rand = new Random();
        return possiblePositions.get(rand.nextInt(possiblePositions.size()));
    }
}
