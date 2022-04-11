package model;

import controller.Generation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoundTest {
    @Test
    public void testMovePlayerUp() {
        var field = new Field();
        Generation generation = new Generation();
        field = new Field(generation);
        Round round = new Round(generation.getPlayer(), generation.getEnemies(), field);
        Position currentPosition = round.getPlayer().getPosition();
        Position movePosition = new Position(currentPosition.getX(), currentPosition.getY() - 1);
        if (checkPosition(field, movePosition)) {
            System.out.println(currentPosition.getX());
            System.out.println(currentPosition.getY());
            round.movePlayerUp();
            System.out.println(movePosition.getX());
            System.out.println(movePosition.getY());
            Assertions.assertEquals(movePosition, round.getPlayer().getPosition());
        } else {
            Assertions.assertEquals(new Position(currentPosition.getX(), currentPosition.getY()), round.getPlayer().getPosition());
        }
    }

    @Test
    public void testMovePlayerDown() {
        var field = new Field();
        Generation generation = new Generation();
        field = new Field(generation);
        Round round = new Round(generation.getPlayer(), generation.getEnemies(), field);
        Position currentPosition = round.getPlayer().getPosition();
        Position movePosition = new Position(currentPosition.getX(), currentPosition.getY() + 1);
        if (checkPosition(field, movePosition)) {
            round.movePlayerDown();
            Assertions.assertEquals(movePosition, round.getPlayer().getPosition());
        } else {
            Assertions.assertEquals(new Position(currentPosition.getX(), currentPosition.getY()), round.getPlayer().getPosition());
        }
    }

    @Test
    public void testMovePlayerLeft() {
        var field = new Field();
        Generation generation = new Generation();
        field = new Field(generation);
        Round round = new Round(generation.getPlayer(), generation.getEnemies(), field);
        Position currentPosition = round.getPlayer().getPosition();
        Position movePosition = new Position(currentPosition.getX() - 1, currentPosition.getY());
        if (checkPosition(field, movePosition)) {
            round.movePlayerLeft();
            Assertions.assertEquals(movePosition, round.getPlayer().getPosition());
        } else {
            Assertions.assertEquals(new Position(currentPosition.getX(), currentPosition.getY()), round.getPlayer().getPosition());
        }
    }

    @Test
    public void testMovePlayerRight() {
        var field = new Field();
        Generation generation = new Generation();
        field = new Field(generation);
        Round round = new Round(generation.getPlayer(), generation.getEnemies(), field);
        Position currentPosition = round.getPlayer().getPosition();
        Position movePosition = new Position(currentPosition.getX() + 1, currentPosition.getY());
        if (checkPosition(field, movePosition)) {
            round.movePlayerRight();
            Assertions.assertEquals(movePosition, round.getPlayer().getPosition());
        } else {
            Assertions.assertEquals(new Position(currentPosition.getX(), currentPosition.getY()), round.getPlayer().getPosition());
        }
    }

    private boolean checkPosition(Field field, Position position) {
        if (field.isValidPosition(position)) {
            Cell cell = field.getCell(position);
            return cell == null || cell instanceof EmptyCell;
        } else {
            return false;
        }
    }
}
