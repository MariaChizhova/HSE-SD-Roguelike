package model.decorator;

import model.Position;
import model.strategies.StrategyEnemy;

import java.io.Serializable;

public abstract class StrategyDecorator implements StrategyEnemy, Serializable {
    protected StrategyEnemy decorate;

    public StrategyDecorator(StrategyEnemy decorate) {
        this.decorate = decorate;
    }

    @Override
    public Position nextMove(Position playerPosition, Position enemyPosition) {
        return decorate.nextMove(playerPosition, enemyPosition);
    }
}
