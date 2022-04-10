package controller;

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

    /**
     * Creates GameController instance
     */
    public GameController(ConsoleDrawer view) {
        field = new Field();
        mainMenuState = MainMenuState.START;
        menuState = MenuState.CONTINUE;
        this.screenType = ScreenType.MAIN_MENU;
        this.view = view;
        this.screen = view.getScreen();
        this.round = new Round(field.getPlayer(), field.getEnemies(), field);
        this.inputHandler = new InputHandler(round);
    }

    /**
     * Select screen type
     * @throws IOException
     */
    public void selectMode() throws IOException {
       while (true) {
            KeyType keyType = screen.readInput().getKeyType();
            switch (screenType) {
                case MAIN_MENU:
                    mainMenuState = inputHandler.processMainMenuCommand(keyType, mainMenuState);
                    view.drawMainMenu(mainMenuState);
                    // TODO: Move the processing of KeyType.Enter to the InputHandler
                    if (keyType == KeyType.Enter) {
                        switch (mainMenuState) {
                            case START:
                                startGame();
                            case LOAD_GAME:
                                GameSaverLoader.loadGame(round);
                            case EXIT:
                                exitGame();
                        }
                        screenType = ScreenType.GAME;
                    }
                case GAME:
                    // Change field
                    field = new Field();
                    view.drawMap(field);
                    inputHandler.processGameCommand(keyType);
                    break;
                case MENU:
                    menuState = inputHandler.processMenuCommand(keyType, menuState);
                    view.drawMenu(menuState);
                    // TODO: Move the processing of KeyType.Enter to the InputHandler
                    if (keyType == KeyType.Enter) {
                        switch (menuState) {
                            case CONTINUE:
                                // TODO: continue game
                                screenType = ScreenType.GAME;
                            case SAVE_AND_EXIT:
                                GameSaverLoader.saveGame(round);
                                exitGame();
                                screenType = ScreenType.MAIN_MENU;
                            case EXIT:
                                exitGame();
                                screenType = ScreenType.MAIN_MENU;
                        }
                    }
                    break;
            }
        }
    }

    /**
     * Starting the new game
     */
    public static void startGame() {
        // TODO:
    }

    /**
     * Exiting the game
     */
    public void exitGame() throws IOException {
        // TODO:
        view.closeAll();
    }

}
