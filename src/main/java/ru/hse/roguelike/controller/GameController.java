package ru.hse.roguelike.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import ru.hse.roguelike.controller.commands.*;
import ru.hse.roguelike.model.Field;
import ru.hse.roguelike.model.Round;
import ru.hse.roguelike.view.ConsoleDrawer;
import ru.hse.roguelike.view.MainMenuState;
import ru.hse.roguelike.view.MenuState;
import ru.hse.roguelike.view.ScreenType;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents GameController that responsible for choosing the mode of the game such as:
 * mode in the main menu (start, load game, exit),
 * entering sizes of the screen,
 * mode in the menu (continue, save and exit, exit)
 * game
 */
public class GameController {

    private ConsoleDrawer view;
    private Round round;
    private Screen screen;
    private ScreenType screenType;
    private InputHandler inputHandler;
    private MainMenuState mainMenuState;
    private MenuState menuState;
    private Field field;

    private MapGenerator generation;
    private Integer fieldWidth = null;
    private Integer fieldHeight = null;
    private boolean curFillIsWidth = true;
    private DrawMapCommand drawMapCommand;
    private DrawAskSizeCommand drawAskSizeCommand;
    private DrawMenuCommand drawMenuCommand;
    private DrawMainMenuCommand drawMainMenuCommand;
    private DrawExceptionCommand drawExceptionCommand;
    private boolean notExit = true;

    private final int minFieldHeight = 10;

    private final int maxFieldHeight = 20;

    private final int minFieldWidth = 10;

    private final int maxFieldWidth = 23;


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
     * Select screen type (MAIN_MENU, ASK_SIZE, GAME, MENU) depending on the screenType variable
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
                    ask_size(keyType, keyStroke);
                    break;
                case GAME:
                    gameProcessing(keyType);
                    break;
                case MENU:
                    menuProcessing(keyType);
                    break;
            }
        }
    }

    private void enterInput() {
        if (fieldHeight >= maxFieldHeight || fieldHeight <= minFieldHeight
                || fieldWidth >= maxFieldWidth || fieldWidth <= minFieldWidth) {
            drawAskSizeCommand = new DrawAskSizeCommand(view, null, null, false, false);
            drawAskSizeCommand.execute();
        } else {
            generation = new MapGenerator(fieldWidth, fieldHeight);
            startGame(generation);
            screenType = ScreenType.GAME;
            drawMapCommand = new DrawMapCommand(view, field);
            drawMapCommand.execute();
        }
        fieldHeight = null;
        fieldWidth = null;
        curFillIsWidth = true;
    }

    /**
     * Starting the new game
     */

    public void startGame(MapGenerator mapGenerator) {
        field = new Field(mapGenerator);
        round = new Round(mapGenerator.getPlayer(), mapGenerator.getEnemies(), field);
    }

    /**
     * Process different behaviors of main menu
     *
     * @param keyType current screen key type
     */
    public void mainMenuProcessing(KeyType keyType) throws IOException {
        mainMenuState = inputHandler.processMainMenuCommand(keyType, mainMenuState);
        drawMainMenuCommand = new DrawMainMenuCommand(view, mainMenuState);
        drawMainMenuCommand.execute();
        // TODO: Move the processing of KeyType.Enter to the InputHandler
        if (keyType == KeyType.Enter) {
            switch (mainMenuState) {
                case START:
                    screenType = ScreenType.ASK_SIZE;
                    drawAskSizeCommand = new DrawAskSizeCommand(view, null, null, true, true);
                    drawAskSizeCommand.execute();
                    break;
                case LOAD_GAME:
                    round = GameSaverLoader.loadGame();
                    if (round == null) {
                        break;
                    }
                    field = round.getField();
                    screenType = ScreenType.GAME;
                    inputHandler.processGameCommand(keyType, round);
                    drawMapCommand = new DrawMapCommand(view, field);
                    drawMapCommand.execute();
                    break;
                case EXIT:
                    view.closeAll();
                    notExit = false;
                    break;
            }
        }
    }


    /**
     * Process different behaviors of game
     *
     * @param keyType current screen key type
     */
    private void gameProcessing(KeyType keyType) {
        boolean finished = inputHandler.processGameCommand(keyType, round);
        if (finished) {
            screenType = ScreenType.MAIN_MENU;
            drawMainMenuCommand = new DrawMainMenuCommand(view, mainMenuState);
            drawMainMenuCommand.execute();
        } else {
            view.drawMap(field);
            if (keyType == KeyType.Escape) {
                screenType = ScreenType.MENU;
                drawMenuCommand = new DrawMenuCommand(view, menuState);
                drawMenuCommand.execute();
            }
        }
    }


    /**
     * Process different behaviors of menu
     *
     * @param keyType current screen key type
     */
    private void menuProcessing(KeyType keyType) throws FileNotFoundException {
        menuState = inputHandler.processMenuCommand(keyType, menuState);
        drawMenuCommand = new DrawMenuCommand(view, menuState);
        drawMenuCommand.execute();
        // TODO: Move the processing of KeyType.Enter to the InputHandler
        if (keyType == KeyType.Enter) {
            switch (menuState) {
                case CONTINUE:
                    // TODO: continue game
                    drawMapCommand = new DrawMapCommand(view, field);
                    drawMapCommand.execute();
                    screenType = ScreenType.GAME;
                    break;
                case SAVE_AND_EXIT:
                    try {
                        GameSaverLoader.saveGame(round);
                        screenType = ScreenType.MAIN_MENU;
                        drawMainMenuCommand = new DrawMainMenuCommand(view, mainMenuState);
                        drawMainMenuCommand.execute();
                    } catch (Exception ex) {
                        screenType = ScreenType.MAIN_MENU;
                        drawMainMenuCommand = new DrawMainMenuCommand(view, mainMenuState);
                        drawMainMenuCommand.execute();
                        drawExceptionCommand = new DrawExceptionCommand(view, ex.getMessage());
                    }
                    break;
                case EXIT:
                    screenType = ScreenType.MAIN_MENU;
                    drawMainMenuCommand = new DrawMainMenuCommand(view, mainMenuState);
                    drawMainMenuCommand.execute();
                    break;
            }
        }
    }

    private void ask_size(KeyType keyType, KeyStroke keyStroke) {
        if (keyType == KeyType.Enter) {
            if (curFillIsWidth) {
                curFillIsWidth = false;
            } else {
                enterInput();
            }
        } else if (keyType == KeyType.Character) {
            var c = inputHandler.getNumber(keyStroke);
            if (c != null) {
                if (curFillIsWidth) {
                    if (fieldWidth == null) {
                        fieldWidth = Character.getNumericValue(c);
                        drawAskSizeCommand = new DrawAskSizeCommand(view, c, null, true, false);
                        drawAskSizeCommand.execute();
                    } else {
                        fieldWidth = fieldWidth * 10 + Character.getNumericValue(c);
                        drawAskSizeCommand = new DrawAskSizeCommand(view, c, null, true, false);
                        drawAskSizeCommand.execute();
                        curFillIsWidth = false;
                    }
                } else {
                    if (fieldHeight == null) {
                        fieldHeight = Character.getNumericValue(c);
                        drawAskSizeCommand = new DrawAskSizeCommand(view, null, c, true, false);
                        drawAskSizeCommand.execute();
                    } else {
                        fieldHeight = fieldHeight * 10 + Character.getNumericValue(c);
                        enterInput();
                    }
                }
            }
        } else if (keyType == KeyType.Backspace) {
            generation = new MapGenerator("maps/map.txt");
            startGame(generation);
            screenType = ScreenType.GAME;
            drawMapCommand = new DrawMapCommand(view, field);
            drawMapCommand.execute();
        }
    }

}
