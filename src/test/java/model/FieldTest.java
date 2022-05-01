package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FieldTest {
    @Test
    public void testIsValidPositionMethod() {
        var field = new Field(19, 13);
        Assertions.assertFalse(field.isValidPosition(new Position(100, 100)));
        Assertions.assertTrue(field.isValidPosition(new Position(1, 1)));
    }
}
