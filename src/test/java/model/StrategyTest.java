package model;

import model.strategies.SimpleStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class StrategyTest {

    @Test
    public void testSimpleStrategy() {
        var simpleStrategy = new SimpleStrategy();
        var playerPosition = new Position(2, 2);
        var enemyPosition = new Position(6, 6);
        Assertions.assertEquals(enemyPosition, simpleStrategy.nextMove(playerPosition, enemyPosition, 5, Collections.emptyList()));
    }
}
