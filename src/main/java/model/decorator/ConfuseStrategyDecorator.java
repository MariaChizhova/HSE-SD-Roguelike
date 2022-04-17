package model.decorator;

import model.Position;
import model.strategies.SimpleStrategy;
import model.strategies.StrategyEnemy;

import java.io.Serializable;

public class ConfuseStrategyDecorator extends StrategyDecorator implements Serializable {
    private int confusedNumber;

    public ConfuseStrategyDecorator(StrategyEnemy decorate, int confusedNumber) {
        super(decorate);
        this.confusedNumber = confusedNumber;
    }

    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition) {
        if (confusedNumber > 0) {
            --confusedNumber;
            return new SimpleStrategy().nextMove(playerPosition, enemyPosition);
        }
        return decorate.nextMove(playerPosition, enemyPosition);
    }
}
