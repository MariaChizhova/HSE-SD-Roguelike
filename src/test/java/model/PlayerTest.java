package model;

import model.inventory.Artifact;
import model.inventory.ArtifactName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.strategies.SimpleStrategy;

public class PlayerTest {

    @Test
    public void testBeAttacked() {
        var player = new Player(new Position(2, 2));
        var character = new Player(new Position(2, 3));
        int health = player.getHealth();
        double k = 0.06;
        int armor = 10;
        health -= character.getDamage() * (1 - (k * armor) / (1 + k * armor));
        player.beAttacked(character);
        Assertions.assertEquals(health, player.getHealth());
    }

    @Test
    public void testIsAlive() {
        var player = new Player(new Position(2, 2));
        Assertions.assertFalse(player.isDead());
    }

    @Test
    public void testIsDead() {
        var player = new Player(new Position(2, 2));
        var character = new Player(new Position(2, 3));
        for (int i = 0; i < 20; i++) {
            player.beAttacked(character);
        }
        Assertions.assertTrue(player.isDead());
    }

    @Test
    public void testNextMove() {
        var newPosition = new Position(5, 5);
        var player = new Player(new Position(2, 2));
        Assertions.assertEquals(newPosition, player.move(newPosition));
    }

    @Test
    public void testIncreaseHealth() {
        var player = new Player(new Position(2, 2));
        int health = player.getHealth();
        int extraHealth = 10;
        player.increaseHealth(extraHealth);
        Assertions.assertEquals(health + extraHealth, player.getHealth());
    }

    @Test
    public void testHasArtifact() {
        var player = new Player(new Position(2, 2));
        Assertions.assertFalse(player.hasArtifact(ArtifactName.BOOTS));
    }

    @Test
    public void testAddArtifact() {
        var player = new Player(new Position(2, 2));
        player.addArtifact(new Artifact(ArtifactName.BOOTS, 5, 5));
        Assertions.assertTrue(player.hasArtifact(ArtifactName.BOOTS));
    }

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
