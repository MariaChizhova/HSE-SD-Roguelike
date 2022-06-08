package contoller;

import java.awt.Menu;

import controller.InputHandler;
import controller.MapGenerator;
import model.EmptyCell;
import model.Field;
import model.Position;
import model.Round;
import model.Wall;
import org.junit.jupiter.api.Test;
import view.MainMenuState;
import view.MenuState;

import static com.googlecode.lanterna.input.KeyType.ArrowDown;
import static com.googlecode.lanterna.input.KeyType.ArrowLeft;
import static com.googlecode.lanterna.input.KeyType.ArrowUp;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputHandlerTest {
    InputHandler inputHandler = new InputHandler();

    @Test
    public void testProcessMainMenuCommand() {
        var downFromStart = inputHandler.processMainMenuCommand(ArrowDown, MainMenuState.START);
        assertEquals(MainMenuState.LOAD_GAME, downFromStart);

        var upFromStart = inputHandler.processMainMenuCommand(ArrowUp, MainMenuState.START);
        assertEquals(MainMenuState.EXIT, upFromStart);

        var startNoDiff = inputHandler.processMainMenuCommand(ArrowLeft, MainMenuState.START);
        assertEquals(MainMenuState.START, startNoDiff);

        var downFromLoad = inputHandler.processMainMenuCommand(ArrowDown, MainMenuState.LOAD_GAME);
        assertEquals(MainMenuState.EXIT, downFromLoad);

        var upFromLoad = inputHandler.processMainMenuCommand(ArrowUp, MainMenuState.LOAD_GAME);
        assertEquals(MainMenuState.START, upFromLoad);

        var loadNoDiff = inputHandler.processMainMenuCommand(ArrowLeft, MainMenuState.LOAD_GAME);
        assertEquals(MainMenuState.LOAD_GAME, loadNoDiff);
    }

    @Test
    public void testProcessMenuCommand() {
        var downFromContinue = inputHandler.processMenuCommand(ArrowDown, MenuState.CONTINUE);
        assertEquals(MenuState.EXIT, downFromContinue);

        var upFromContinue = inputHandler.processMenuCommand(ArrowUp, MenuState.CONTINUE);
        assertEquals(MenuState.SAVE_AND_EXIT, upFromContinue);

        var continueNoDiff = inputHandler.processMenuCommand(ArrowLeft, MenuState.CONTINUE);
        assertEquals(MenuState.CONTINUE, continueNoDiff);

        var downFromExit = inputHandler.processMenuCommand(ArrowDown, MenuState.EXIT);
        assertEquals(MenuState.SAVE_AND_EXIT, downFromExit);

        var upFromExit = inputHandler.processMenuCommand(ArrowUp, MenuState.EXIT);
        assertEquals(MenuState.CONTINUE, upFromExit);

        var exitNoDiff = inputHandler.processMenuCommand(ArrowLeft, MenuState.EXIT);
        assertEquals(MenuState.EXIT, exitNoDiff);
    }

    @Test
    public void testProcessGameCommand() {
        MapGenerator mapGenerator = new MapGenerator();
        var field = new Field(mapGenerator);
        Round round = new Round(mapGenerator.getPlayer(), mapGenerator.getEnemies(), field);
        Position curPos = round.getPlayer().getPosition();
        if (field.isInsideBounds(curPos.getX(), curPos.getY() + 1)) {
            var cell = field.getCell(curPos.getX(), curPos.getY() + 1);
            if (cell instanceof EmptyCell) {
                inputHandler.processGameCommand(ArrowUp, round);
                assertEquals(new Position(curPos.getX(), curPos.getY() + 1), round.getPlayer().getPosition());
            }
        }

    }
}
