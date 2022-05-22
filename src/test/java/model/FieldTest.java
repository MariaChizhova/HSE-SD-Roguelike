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
}
