package controller;

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
            KeyType keyType = screen.readInput().getKeyType();
            switch (screenType) {
                case MAIN_MENU:
                    mainMenuProcessing(keyType);
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

    /**
     * Starting the new game
     */
    public void gamePreparing() {
        mapGenerator = new MapGenerator();
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
                    gamePreparing();
                    screenType = ScreenType.GAME;
                    inputHandler.processGameCommand(keyType, round);
                    view.drawMap(field);
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
    private void menuProcessing(KeyType keyType) throws FileNotFoundException {
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
