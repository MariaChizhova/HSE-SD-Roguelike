package model;

import model.inventory.Artifact;
import model.inventory.ArtifactName;
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

    @Test
    public void addArtifactTest() {
        var player = new Player(new Position(5, 5));
        var oldArmor = player.getArmor();
        var oldDamage = player.getDamage();
        var woodenSword = new Artifact(ArtifactName.WOODEN_SWORD, 20, 0);
        var helmet = new Artifact(ArtifactName.HELMET, 0, 30);
        player.addArtifact(woodenSword);
        player.addArtifact(helmet);
        Assertions.assertEquals(30, player.getArmor() - oldArmor);
        Assertions.assertEquals(20, player.getDamage() - oldDamage);
    }
}
