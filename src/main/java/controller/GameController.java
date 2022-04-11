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
    private Generation generation;

    /**
     * Creates GameController instance
     */
    public GameController(ConsoleDrawer view) {
        generation = new Generation();
        field = new Field(generation);
        mainMenuState = MainMenuState.START;
        menuState = MenuState.CONTINUE;
        this.screenType = ScreenType.MAIN_MENU;
        view.drawMainMenu(mainMenuState);
        this.view = view;
        this.screen = view.getScreen();
        this.round = new Round(generation.getPlayer(), generation.getEnemies(), field);
        this.inputHandler = new InputHandler();
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
                                screenType = ScreenType.GAME;
                            case LOAD_GAME:
                                GameSaverLoader.loadGame(round);
                                screenType = ScreenType.GAME;
                            case EXIT:
                                exitGame();
                        }
                    }
                case GAME:
                    // Change field
                    generation = new Generation();
                    field.updateGeneration(generation);
                    view.drawMap(field);
                    inputHandler.processGameCommand(keyType, round);
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
