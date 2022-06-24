package ru.hse.roguelike.model;

import ru.hse.roguelike.model.strategies.AggressiveStrategy;
import ru.hse.roguelike.model.strategies.CowardStrategy;
import ru.hse.roguelike.model.strategies.SimpleStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import java.util.List;

public class StrategyTest {

    @Test
    public void testSimpleStrategy() {
        var simpleStrategy = new SimpleStrategy();
        var playerPosition = new Position(2, 2);
        var enemyPosition = new Position(6, 6);
        Assertions.assertEquals(enemyPosition, simpleStrategy.nextMove(playerPosition, enemyPosition, 5, Collections.emptyList()));
    }

    @Test
    public void testCowardStrategyStay() {
        var cowardStrategy = new CowardStrategy();
        var playerPosition = new Position(2, 2);
        var enemyPosition = new Position(6, 6);
        Assertions.assertEquals(enemyPosition, cowardStrategy.nextMove(playerPosition, enemyPosition, 1, List.of()));
    }

    @Test
    public void testCowardStrategyGo() {
        var cowardStrategy = new CowardStrategy();
        var playerXPoses = List.of(2, 2, 2, 6);
        var playerYPoses = List.of(2, 6, 2, 2);
        var enemyXPoses = List.of(2, 2, 6, 2);
        var enemyYPoses = List.of(6, 2, 2, 2);
        var resXPoses = List.of(2, 2, 7, 1);
        var resYPoses = List.of(7, 1, 2, 2);
        for (int i = 0; i < playerXPoses.size(); i++) {
            var playerPosition = new Position(playerXPoses.get(i), playerYPoses.get(i));
            var enemyPosition = new Position(enemyXPoses.get(i), enemyYPoses.get(i));
            var newEnemyPosition = new Position(resXPoses.get(i), resYPoses.get(i));
            Assertions.assertEquals(newEnemyPosition, cowardStrategy.nextMove(playerPosition, enemyPosition, 5, List.of()));
        }
    }

    @Test
    public void testAggressiveStrategy() {
        var aggressiveStrategy = new AggressiveStrategy();
        var playerPosition = new Position(2, 2);
        var enemyPosition = new Position(6, 6);
        Assertions.assertEquals(enemyPosition, aggressiveStrategy.nextMove(playerPosition, enemyPosition, 1, List.of()));
    }

    @Test
    public void testAggressiveStrategyGo() {
        var aggressiveStrategy = new AggressiveStrategy();
        var playerXPoses = List.of(2, 2, 2, 6);
        var playerYPoses = List.of(2, 6, 2, 2);
        var enemyXPoses = List.of(2, 2, 6, 2);
        var enemyYPoses = List.of(6, 2, 2, 2);
        var resXPoses = List.of(2, 2, 5, 3);
        var resYPoses = List.of(5, 3, 2, 2);
        for (int i = 0; i < playerXPoses.size(); i++) {
            var playerPosition = new Position(playerXPoses.get(i), playerYPoses.get(i));
            var enemyPosition = new Position(enemyXPoses.get(i), enemyYPoses.get(i));
            var newEnemyPosition = new Position(resXPoses.get(i), resYPoses.get(i));
            Assertions.assertEquals(newEnemyPosition, aggressiveStrategy.nextMove(playerPosition, enemyPosition, 5, List.of()));
        }
    }
}
