package contoller;

import controller.MapGenerator;
import model.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapGeneratorTest {
    MapGenerator map = new MapGenerator(19, 13);

    @Test
    public void testGetPlayer() {
        var player = map.getPlayer();
        var field = new Field(map);
        assertNotNull(player);
        assertTrue(field.isInsideBounds(player.getPosition()));
    }

    @Test
    public void testGetEnemies() {
        var enemies = map.getEnemies();
        var field = new Field(map);
        assertTrue(enemies != null && !enemies.isEmpty());
        enemies.forEach(enemy ->
                assertTrue(field.isInsideBounds(enemy.getPosition()))
        );
    }

    @Test
    public void testGetGenerations() {
        var generation = map.getGeneration();
        generation.forEach(Assertions::assertNotNull);
    }
}
