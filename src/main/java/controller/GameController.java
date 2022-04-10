package controller;

import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import model.Round;
import view.ConsoleDrawer;
import view.MainMenuState;
import view.MenuState;
import view.ScreenType;

import java.io.IOException;

import static view.ScreenType.GAME;

public class GameController {

    private ConsoleDrawer view;
    private Round round;
    private Screen screen;
    private ScreenType screenType;
    private InputHandler inputHandler;
    private InputProvider inputProvider;
    private MainMenuState mainMenuState;
    private MenuState menuState;

    public GameController(ConsoleDrawer view) {
        mainMenuState = MainMenuState.START;
        //menuState = MenuState.CONTINUE;
        this.screenType = ScreenType.MAIN_MENU;
        this.view = view;
        this.screen = view.getScreen();
        this.inputHandler = new InputHandler(/*round*/);
        //  this.round = new Round();
    }


    public void selectMode() throws IOException {
       while (true) {
            KeyType keyType = screen.readInput().getKeyType();
            switch (screenType) {
                case MAIN_MENU:
                    mainMenuState = inputHandler.processMainMenuCommand(keyType, mainMenuState);
                    view.drawMainMenu(mainMenuState);
                    if (mainMenuState == MainMenuState.EXIT && keyType == KeyType.Enter) {
                        exitGame();
                    }
                case GAME:
                    // TODO:
                    break;
                case MENU:
                    menuState = inputHandler.processMenuCommand(keyType, menuState);
                    view.drawMenu(menuState);
                    break;
            }
        }
    }

    public void startGame() {

    }

    public void exitGame() throws IOException {
        // TODO:
        view.closeAll();
    }

}
