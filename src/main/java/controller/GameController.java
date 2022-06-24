package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import model.Field;
import model.Round;
import view.ConsoleDrawer;
import view.MainMenuState;
import view.MenuState;
import view.ScreenType;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GameController {

    private ConsoleDrawer view;
    private Round round;
    private Screen screen;
    private ScreenType screenType;
    private InputHandler inputHandler;
    private MainMenuState mainMenuState;
    private MenuState menuState;
    private Field field;
    private MapGenerator mapGenerator;
    private boolean notExit = true;
    private Integer fieldWidth = null;
    private Integer fieldHeight = null;
    private boolean curFillIsWidth = true;

    /**
     * Creates GameController instance
     */
    public GameController() {
        this.view = new ConsoleDrawer();
        mainMenuState = MainMenuState.START;
        menuState = MenuState.CONTINUE;
        this.screenType = ScreenType.MAIN_MENU;
        view.drawMainMenu(mainMenuState);
        this.screen = view.getScreen();
        this.inputHandler = new InputHandler();
    }

    /**
     * Select screen type
     *
     * @throws IOException
     */
    public void selectMode() throws IOException {
        while (notExit) {
            KeyStroke keyStroke = screen.readInput();
            KeyType keyType = keyStroke.getKeyType();
            switch (screenType) {
                case MAIN_MENU:
                    mainMenuProcessing(keyType);
                    break;
                case ASK_SIZE:
                    inputSizeProcessing(keyStroke, keyType);
                    break;
                case GAME:
                    boolean finished = inputHandler.processGameCommand(keyType, round);
                    if (finished) {
                        screenType = ScreenType.MAIN_MENU;
                        view.drawMainMenu(mainMenuState);
                    } else {
                        view.drawMap(field);
                        if (keyType == KeyType.Escape) {
                            screenType = ScreenType.MENU;
                            view.drawMenu(menuState);
                        }
                    }
                    break;
                case MENU:
                    menuProcessing(keyType);
                    break;
            }
        }
    }

    /**
     * Starting the new game
     */
    public void gamePreparing(int fieldWidth, int fieldHeight) {
        mapGenerator = new MapGenerator(fieldWidth, fieldHeight);
        field = new Field(mapGenerator);
        round = new Round(mapGenerator.getPlayer(), mapGenerator.getEnemies(), field);
    }

    /**
     * Process different behaviors of main menu
     * @param keyType current screen key type
     */
    public void mainMenuProcessing(KeyType keyType) throws IOException {
        mainMenuState = inputHandler.processMainMenuCommand(keyType, mainMenuState);
        view.drawMainMenu(mainMenuState);
        // TODO: Move the processing of KeyType.Enter to the InputHandler
        if (keyType == KeyType.Enter) {
            switch (mainMenuState) {
                case START:
                    screenType = ScreenType.ASK_SIZE;
                    view.drawAskSize(null, null, true, true);
                    break;
                case LOAD_GAME:
                    round = GameSaverLoader.loadGame();
                    field = round.getField();
                    screenType = ScreenType.GAME;
                    inputHandler.processGameCommand(keyType, round);
                    view.drawMap(field);
                    break;
                case EXIT:
                    view.closeAll();
                    notExit = false;
                    break;
            }
        }
    }

    private void checkFieldCorrectness(KeyType keyType) {
        if (fieldHeight > 20 || fieldHeight < 10
                || fieldWidth > 23 || fieldWidth < 10) {
            view.drawAskSize(null, null, false, false);
        } else {
            startGame(keyType);
        }
        fieldHeight = null;
        fieldWidth = null;
        curFillIsWidth = true;
    }

    private void startGame(KeyType keyType) {
        gamePreparing(fieldWidth, fieldHeight);
        screenType = ScreenType.GAME;
        inputHandler.processGameCommand(keyType, round);
        view.drawMap(field);
    }

    private void inputSizeProcessing(KeyStroke keyStroke, KeyType keyType) throws IOException {
        switch (keyType) {
            case Enter:
                if (curFillIsWidth) {
                    curFillIsWidth = false;
                } else {
                    checkFieldCorrectness(keyType);
                }
                break;
            case Character:
                var c = inputHandler.getNumber(keyStroke);
                if (c != null) {
                    if (curFillIsWidth) {
                        if (fieldWidth == null) {
                            fieldWidth = Character.getNumericValue(c);
                            view.drawAskSize(c, null, true, false);
                        } else {
                            fieldWidth = fieldWidth * 10 + Character.getNumericValue(c);
                            view.drawAskSize(c, null, true, false);
                            curFillIsWidth = false;
                            break;
                        }
                    } else {
                        if (fieldHeight == null) {
                            fieldHeight = Character.getNumericValue(c);
                            view.drawAskSize(null, c, true, false);
                        } else {
                            fieldHeight = fieldHeight * 10 + Character.getNumericValue(c);
                            checkFieldCorrectness(keyType);
                        }
                    }
                }
                break;
            case Backspace:
                startGame(keyType);
                break;
        }
    }


    /**
     * Process different behaviors of game
     * @param keyType current screen key type
     */
    private void gameProcessing(KeyType keyType) {
        inputHandler.processGameCommand(keyType, round);
        view.drawMap(field);
        if (keyType == KeyType.Escape) {
            screenType = ScreenType.MENU;
            view.drawMenu(menuState);
        }
    }


    /**
     * Process different behaviors of menu
     * @param keyType current screen key type
     */
    private void menuProcessing(KeyType keyType) {
        menuState = inputHandler.processMenuCommand(keyType, menuState);
        view.drawMenu(menuState);
        // TODO: Move the processing of KeyType.Enter to the InputHandler
        if (keyType == KeyType.Enter) {
            switch (menuState) {
                case CONTINUE:
                    // TODO: continue game
                    view.drawMap(field);
                    screenType = ScreenType.GAME;
                    break;
                case SAVE_AND_EXIT:
                    GameSaverLoader.saveGame(round);
                    screenType = ScreenType.MAIN_MENU;
                    view.drawMainMenu(mainMenuState);
                    break;
                case EXIT:
                    screenType = ScreenType.MAIN_MENU;
                    view.drawMainMenu(mainMenuState);
                    break;
            }
        }
    }

}
