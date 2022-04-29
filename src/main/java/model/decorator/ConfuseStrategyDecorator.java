package model.decorator;

import model.Position;
import model.strategies.StrategyEnemy;

import java.io.Serializable;

/**
 * Confusing decorator of enemy strategy
 */
public class ConfuseStrategyDecorator extends StrategyDecorator implements Serializable {
    private int confusedNumber;

    /**
     * Creates ConfusedStrategyDecorator instance
     * @param decorate strategy to decorate
     * @param confusedNumber total number of confusing turns
     */
    public ConfuseStrategyDecorator(StrategyEnemy decorate, int confusedNumber) {
        super(decorate);
        this.confusedNumber = confusedNumber;
    }

    /**
     *  Makes one enemy move
     * @param playerPosition - player's position
     * @param enemyPosition - old position
     * @param visibility - enemy's visibility radius
     * @return new position
     */
    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition, int visibility) {
        if (confusedNumber > 0) {
            --confusedNumber;
            return StrategyEnemy.getRandomStrategy().nextMove(playerPosition, enemyPosition, visibility);
        }
        return decorate.nextMove(playerPosition, enemyPosition, visibility);
    }
}
