package model;

import model.strategies.SimpleStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PlayerTest {

    @Test
    public void attackEnemyTest() {
        var player = new Player(new Position(5, 5));
        var enemy = new Enemy(new Position(5, 4), new SimpleStrategy());
        player.attack(enemy);
        Assertions.assertEquals(92, enemy.getHealth());
    }

    @Test
    public void killEnemyTest() {
        var player = new Player(new Position(5, 5));
        var oldExp = player.getExperience();
        var enemy = new Enemy(new Position(5, 4), new SimpleStrategy());
        for(int i = 0; i < 12; i++) {
            player.attack(enemy);
            Assertions.assertFalse(enemy.isDead());
        }
        player.attack(enemy);
        Assertions.assertTrue(enemy.isDead());
        Assertions.assertEquals(enemy.getExperience(), player.getExperience() - oldExp);
    }

    @Test
    public void increaseLevelTest() {
        var player = new Player(new Position(5, 5));
        var oldLevel = player.getLevel();
        var oldDamage = player.getDamage();
        var oldArmor = player.getArmor();
        for(int i = 0; i < 4; i++) {
            var enemy = new Enemy(new Position(5, 4), new SimpleStrategy());
            for (int j = 0; j < 13; j++) {
                player.attack(enemy);
            }
        }
        Assertions.assertEquals(1, player.getLevel() - oldLevel);
        Assertions.assertEquals(1, player.getArmor() - oldArmor);
        Assertions.assertEquals(1, player.getDamage() - oldDamage);
        Assertions.assertEquals(100, player.getHealth());
    }
}
