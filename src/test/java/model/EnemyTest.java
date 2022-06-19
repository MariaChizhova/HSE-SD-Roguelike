package model;


import ru.hse.roguelike.model.Player;
import ru.hse.roguelike.model.Position;
import ru.hse.roguelike.model.enemy.Enemy;
import ru.hse.roguelike.model.strategies.SimpleStrategy;
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
        for (int i = 0; i < 24; i++) {
            enemy.attack(player);
            Assertions.assertFalse(player.isDead());
        }
        enemy.attack(player);
        Assertions.assertTrue(player.isDead());
    }

    @Test
    public void testIsAlive() {
        var enemy = new Enemy(new Position(5, 4), new SimpleStrategy(), "default", 5, 5, 5);
        Assertions.assertFalse(enemy.isDead());
    }

    @Test
    public void testIsDead() {
        var enemy = new Enemy(new Position(2, 2), new SimpleStrategy(), "default", 5, 5, 5);
        var character = new Enemy(new Position(2, 3), new SimpleStrategy(), "default", 5, 5, 5);
        for (int i = 0; i < 26; i++) {
            enemy.beAttacked(character);
        }
        Assertions.assertTrue(enemy.isDead());
    }

    @Test
    public void testNextMove() {
        var position = new Position(2, 2);
        var enemy = new Enemy(position, new SimpleStrategy(), "default", 5, 5, 5);
        Assertions.assertEquals(position, enemy.move(position));
    }
}
