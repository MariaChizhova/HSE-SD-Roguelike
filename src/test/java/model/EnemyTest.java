package model;

import model.enemy.Enemy;
import model.strategies.SimpleStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class EnemyTest {

    @Test
    public void attackPlayerTest() {
        var player = new Player(new Position(5, 5));
        var enemy = new Enemy(new Position(5, 4), new SimpleStrategy(), "default", 5, 5, 5);
        enemy.attack(player);
        Assertions.assertEquals(96, player.getHealth());
    }

    @Test
    public void killPlayerTest() {
        var player = new Player(new Position(5, 5));
        var enemy = new Enemy(new Position(5, 4), new SimpleStrategy(), "default", 5, 5, 5);
        for(int i = 0; i < 24; i++) {
            enemy.attack(player);
            Assertions.assertFalse(player.isDead());
        }
        enemy.attack(player);
        Assertions.assertTrue(player.isDead());
    }
}
