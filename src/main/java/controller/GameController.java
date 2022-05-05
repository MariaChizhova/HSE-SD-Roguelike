package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import controller.commands.DrawAskSizeCommand;
import controller.commands.DrawMainMenuCommand;
import controller.commands.DrawMapCommand;
import controller.commands.DrawMenuCommand;
import model.Field;
import model.Round;
import view.ConsoleDrawer;
import view.MainMenuState;
import view.MenuState;
import view.ScreenType;

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
    private Generation generation;
    private Integer fieldWidth = null;
    private Integer fieldHeight = null;
    private boolean curFillIsWidth = true;
    private DrawMapCommand drawMapCommand;
    private DrawAskSizeCommand drawAskSizeCommand;
    private DrawMenuCommand drawMenuCommand;
    private DrawMainMenuCommand drawMainMenuCommand;

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
        boolean notExit = true;
        while (notExit) {
            KeyStroke keyStroke = screen.readInput();
            KeyType keyType = keyStroke.getKeyType();
            switch (screenType) {
                case MAIN_MENU:
                    mainMenuState = inputHandler.processMainMenuCommand(keyType, mainMenuState);
                    drawMainMenuCommand = new DrawMainMenuCommand(view, mainMenuState);
                    drawMainMenuCommand.execute();
                    // TODO: Move the processing of KeyType.Enter to the InputHandler
                    if (keyType == KeyType.Enter) {
                        switch (mainMenuState) {
                            case START:
                                screenType = ScreenType.ASK_SIZE;
                                drawAskSizeCommand = new DrawAskSizeCommand(view, null, null, true);
                                drawAskSizeCommand.execute();
                                break;
                            case LOAD_GAME:
                                round = GameSaverLoader.loadGame();
                                if (round == null) {
                                    continue;
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
                    break;
                case ASK_SIZE:
                    if (keyType == KeyType.Enter) {
                        if (curFillIsWidth) {
                            curFillIsWidth = false;
                        } else {
                            if (fieldHeight > 20 || fieldHeight < 10
                                    || fieldWidth > 23 || fieldWidth < 10) {
                                fieldHeight = null;
                                fieldWidth = null;
                                curFillIsWidth = true;
                                drawAskSizeCommand = new DrawAskSizeCommand(view, null, null, false);
                                drawAskSizeCommand.execute();
                            } else {
                                generation = new Generation(fieldWidth, fieldHeight);
                                startGame(generation);
                                screenType = ScreenType.GAME;
                                drawMapCommand = new DrawMapCommand(view, field);
                                drawMapCommand.execute();
                            }
                        }
                    } else if (keyType == KeyType.Character) {
                        var c = inputHandler.getNumber(keyStroke);
                        DrawAskSizeCommand drawAskSizeCommand;
                        if (c != null) {
                            if (curFillIsWidth) {
                                if (fieldWidth == null) {
                                    fieldWidth = Character.getNumericValue(c);
                                    drawAskSizeCommand = new DrawAskSizeCommand(view, null, null, false);
                                    drawAskSizeCommand.execute();
                                } else {
                                    fieldWidth = fieldWidth * 10 + Character.getNumericValue(c);
                                    drawAskSizeCommand = new DrawAskSizeCommand(view, null, null, true);
                                    drawAskSizeCommand.execute();
                                    curFillIsWidth = false;
                                    continue;
                                }
                            } else {
                                if (fieldHeight == null) {
                                    fieldHeight = Character.getNumericValue(c);
                                    drawAskSizeCommand = new DrawAskSizeCommand(view, null, c, true);
                                    drawAskSizeCommand.execute();
                                } else {
                                    fieldHeight = fieldHeight * 10 + Character.getNumericValue(c);
                                    if (fieldHeight > 20 || fieldHeight < 10
                                            || fieldWidth > 23 || fieldWidth < 10) {
                                        fieldHeight = null;
                                        fieldWidth = null;
                                        curFillIsWidth = true;
                                        drawAskSizeCommand = new DrawAskSizeCommand(view, null, null, false);
                                        drawAskSizeCommand.execute();
                                    } else {
                                        generation = new Generation(fieldWidth, fieldHeight);
                                        startGame(generation);
                                        screenType = ScreenType.GAME;
                                        drawMapCommand = new DrawMapCommand(view, field);
                                        drawMapCommand.execute();
                                    }
                                }
                            }
                        }
                    } else if (keyType == KeyType.Backspace) {
                        generation = new Generation("maps/map.txt");
                        startGame(generation);
                        screenType = ScreenType.GAME;
                        drawMapCommand = new DrawMapCommand(view, field);
                        drawMapCommand.execute();
                    }
                    break;
                case GAME:
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
                    break;
                case MENU:
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
                                GameSaverLoader.saveGame(round);
                                screenType = ScreenType.MAIN_MENU;
                                drawMainMenuCommand = new DrawMainMenuCommand(view, mainMenuState);
                                drawMainMenuCommand.execute();
                                break;
                            case EXIT:
                                screenType = ScreenType.MAIN_MENU;
                                drawMainMenuCommand = new DrawMainMenuCommand(view, mainMenuState);
                                drawMainMenuCommand.execute();
                                break;
                        }
                    }
                    break;
            }
        }
    }

    /**
     * Starting the new game
     */
    public void startGame(Generation generation) {
        field = new Field(generation);
        round = new Round(generation.getPlayer(), generation.getEnemies(), field);
    }

}
