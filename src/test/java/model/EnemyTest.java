package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.strategies.SimpleStrategy;

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

    @Test
    public void attackPlayerTest() {
        var player = new Player(new Position(5, 5));
        var enemy = new Enemy(new Position(5, 4), new SimpleStrategy());
        enemy.attack(player);
        Assertions.assertEquals(96, player.getHealth());
    }

    @Test
    public void killPlayerTest() {
        var player = new Player(new Position(5, 5));
        var enemy = new Enemy(new Position(5, 4), new SimpleStrategy());
        for(int i = 0; i < 24; i++) {
            enemy.attack(player);
            Assertions.assertFalse(player.isDead());
        }
        enemy.attack(player);
        Assertions.assertTrue(player.isDead());
    }
}