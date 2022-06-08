package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FieldTest {
    @Test
    public void testIsValidPositionMethod() {
        var field = new Field();
        Assertions.assertFalse(field.isInsideBounds(new Position(100, 100)));
        Assertions.assertTrue(field.isInsideBounds(new Position(1, 1)));
    }

    @Test
    public void testIsInsideBounds() {
        var field = new Field();
        Assertions.assertTrue(field.isInsideBounds(new Position(5, 6)));
        Assertions.assertFalse(field.isInsideBounds(new Position(5, 22)));
        Assertions.assertFalse(field.isInsideBounds(new Position(5, -22)));
        Assertions.assertFalse(field.isInsideBounds(new Position(35, 6)));
        Assertions.assertFalse(field.isInsideBounds(new Position(-35, 6)));
    }

    @Test
    public void testMovePlayer() {
        var oldPosition = new Position(10, 10);
        var newPosition = new Position(12, 12);
        var player = new Player(oldPosition);
        var field = new Field();
        field.movePlayer(newPosition, player);
        Assertions.assertTrue(field.getCell(oldPosition) instanceof EmptyCell);
        Assertions.assertTrue(field.getCell(newPosition) instanceof Player);
    }

    @Test
    public void testMoveEnemy() {
        var oldPosition = new Position(10, 10);
        var newPosition = new Position(12, 12);
        var enemy = new Enemy(oldPosition, new SimpleStrategy());
        var field = new Field();
        field.moveEnemy(newPosition, enemy);
        Assertions.assertTrue(field.getCell(oldPosition) instanceof EmptyCell);
        Assertions.assertTrue(field.getCell(newPosition) instanceof Enemy);
    }

    @Test
    public void testClearCage() {
        var oldPosition = new Position(10, 10);
        var newPosition = new Position(12, 12);
        var player = new Player(oldPosition);
        var field = new Field();
        field.movePlayer(newPosition, player);
        field.clearCage(newPosition);
        Assertions.assertTrue(field.getCell(newPosition) instanceof EmptyCell);
    }
}
