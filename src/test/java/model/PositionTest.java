package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.Position;

public class PositionTest {
    @Test
    public void testGetX() {
        Position position = new Position(1, 5);
        Assertions.assertEquals(1, position.getX());
    }

    @Test
    public void testGetY() {
        Position position = new Position(1, 5);
        Assertions.assertEquals(5, position.getY());
    }

    @Test
    public void testEqual() {
        Position position1 = new Position(1, 5);
        Position position2 = new Position(1, 5);
        Position position3 = new Position(5, 5);
        Assertions.assertEquals(position1, position2);
        Assertions.assertNotEquals(position1, position3);
        Assertions.assertNotEquals(position2, position3);
    }
}
