package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StrategyTest {

    @Test
    public void testSimpleStrategy() {
        var simpleStrategy = new SimpleStrategy();
        var playerPosition = new Position(2, 2);
        var enemyPosition = new Position(6, 6);
        Assertions.assertEquals(enemyPosition, simpleStrategy.nextMove(playerPosition, enemyPosition));
    }
}
