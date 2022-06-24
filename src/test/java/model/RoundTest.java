package model;

import controller.MapGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class RoundTest {

    @Test
    public void testMovePlayerUp() {
        UnaryOperator<Position> positionStep = (position) -> new Position(position.getX(), position.getY() - 1);
        Consumer<Round> moveAction = Round::movePlayerUp;
        testMovePlayer(positionStep, moveAction);
    }

    @Test
    public void testMovePlayerDown() {
        UnaryOperator<Position> positionStep = (position) -> new Position(position.getX(), position.getY() + 1);
        Consumer<Round> moveAction = Round::movePlayerDown;
        testMovePlayer(positionStep, moveAction);
    }

    @Test
    public void testMovePlayerLeft() {
        UnaryOperator<Position> positionStep = (position) -> new Position(position.getX() - 1, position.getY());
        Consumer<Round> moveAction = Round::movePlayerLeft;
        testMovePlayer(positionStep, moveAction);
    }

    @Test
    public void testMovePlayerRight() {
        UnaryOperator<Position> positionStep = (position) -> new Position(position.getX() + 1, position.getY());
        Consumer<Round> moveAction = Round::movePlayerRight;
        testMovePlayer(positionStep, moveAction);
    }

    void testMovePlayer(UnaryOperator<Position> positionStep, Consumer<Round> moveAction) {
        int width = 19;
        int height = 13;
        var field = new Field(width,height);
        MapGenerator mapGenerator = new MapGenerator(width, height);
        field = new Field(mapGenerator);
        Round round = new Round(mapGenerator.getPlayer(), mapGenerator.getEnemies(), field);
        Position currentPosition = round.getPlayer().getPosition();
        Position movePosition = positionStep.apply(currentPosition);
        if (checkPosition(field, movePosition)) {
            moveAction.accept(round);
            Assertions.assertEquals(movePosition, round.getPlayer().getPosition());
        } else {
            Assertions.assertEquals(new Position(currentPosition.getX(), currentPosition.getY()), round.getPlayer().getPosition());
        }
    }

    private boolean checkPosition(Field field, Position position) {
        if (field.isInsideBounds(position)) {
            Cell cell = field.getCell(position);
            return cell == null || cell instanceof EmptyCell;
        } else {
            return false;
        }
    }
}
