package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnemyTest {

    @Test
    public void testIsAlive() {
        var enemy = new Enemy(new Position(2, 2), new SimpleStrategy());
        Assertions.assertFalse(enemy.isDead());
    }

    @Test
    public void testIsDead() {
        var enemy = new Enemy(new Position(2, 2), new SimpleStrategy());
        var character = new Enemy(new Position(2, 3), new SimpleStrategy());
        for (int i = 0; i < 20; i++) {
            enemy.beAttacked(character);
        }
        Assertions.assertTrue(enemy.isDead());
    }

    @Test
    public void testNextMove() {
        var position = new Position(2, 2);
        var enemy = new Enemy(position, new SimpleStrategy());
        Assertions.assertEquals(position, enemy.move(position));
    }
}
