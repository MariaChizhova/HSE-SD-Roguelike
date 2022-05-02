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
                    view.drawMainMenu(mainMenuState);
                    // TODO: Move the processing of KeyType.Enter to the InputHandler
                    if (keyType == KeyType.Enter) {
                        switch (mainMenuState) {
                            case START:
                                screenType = ScreenType.ASK_SIZE;
                                view.drawAskSize(null, null, true);
                                break;
                            case LOAD_GAME:
                                round = GameSaverLoader.loadGame();
                                if (round == null) {
                                    continue;
                                }
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
                                view.drawAskSize(null, null, false);
                            } else {
                                startGame();
                                screenType = ScreenType.GAME;
                                view.drawMap(field);
                            }
                        }
                    } else if (keyType == KeyType.Character) {
                        var c = inputHandler.getNumber(keyStroke);
                        if (c != null) {
                            if (curFillIsWidth) {
                                if (fieldWidth == null) {
                                    fieldWidth = Character.getNumericValue(c);
                                    view.drawAskSize(c, null, true);
                                } else {
                                    fieldWidth = fieldWidth * 10 + Character.getNumericValue(c);
                                    view.drawAskSize(c, null, true);
                                    curFillIsWidth = false;
                                    continue;
                                }
                            } else {
                                if (fieldHeight == null) {
                                    fieldHeight = Character.getNumericValue(c);
                                    view.drawAskSize(null, c, true);
                                } else {
                                    fieldHeight = fieldHeight * 10 + Character.getNumericValue(c);
                                    if (fieldHeight > 20 || fieldHeight < 10
                                            || fieldWidth > 23 || fieldWidth < 10) {
                                        fieldHeight = null;
                                        fieldWidth = null;
                                        curFillIsWidth = true;
                                        view.drawAskSize(null, null, false);
                                    } else {
                                        startGame();
                                        screenType = ScreenType.GAME;
                                        view.drawMap(field);
                                    }
                                }
                            }
                        }
                    } else if (keyType == KeyType.Backspace) {

                    }
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
                    break;
            }
        }
    }

    /**
     * Starting the new game
     */
    public void startGame() {
        generation = new Generation();
        field = new Field(generation);
        round = new Round(generation.getPlayer(), generation.getEnemies(), field);
    }

}
