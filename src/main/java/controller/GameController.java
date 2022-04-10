package controller;

import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import model.Round;
import view.ConsoleDrawer;
import view.MainMenuState;
import view.ScreenType;

import java.io.IOException;

public class GameController {

    private ConsoleDrawer view;
    private Round round;
    private Screen screen;
    private ScreenType screenType;
    private InputHandler inputHandler;
    private InputProvider inputProvider;
    private MainMenuState mainMenuState;

    public GameController(ConsoleDrawer view) {
        mainMenuState = MainMenuState.START;
        this.screenType = ScreenType.MAIN_MENU;
        view.drawMainMenu(mainMenuState);
        this.view = view;
        this.screen = view.getScreen();
        this.inputHandler = new InputHandler(/*round*/);
        //  this.round = new Round();
    }

    public void selectMode() throws IOException {
        while (true) {
            switch (screenType) {
                case MAIN_MENU:
                    KeyType keyType = screen.readInput().getKeyType();
                    mainMenuState = inputHandler.processMainMenuCommand(keyType, mainMenuState);
                    view.drawMainMenu(mainMenuState);
                case GAME:
                    // TODO:
                    break;
            }
        }
    }

    public void startGame() {

    }

    public void exitGame() {

    }

}
