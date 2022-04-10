package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FieldTest {
    @Test
    public void testIsValidPositionMethod() {
        var field = new Field(Field.FIELD_WIDTH, Field.FIELD_HEIGHT);
        Assertions.assertFalse(field.isValidPosition(new Position(100, 100)));
        Assertions.assertTrue(field.isValidPosition(new Position(1, 1)));
    }
}
