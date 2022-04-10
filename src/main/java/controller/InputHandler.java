package controller;

import com.googlecode.lanterna.input.KeyType;
import model.Round;
import view.MainMenuState;
import view.MenuState;

public class InputHandler {
    //private final Round round;

    /**
     * Creates InputHandler instance
     */
    public InputHandler(/*Round round*/) {
      //  this.round = round;
    }

    /**
     * Processing main menu commands
     * @param command Input command
     */
    public MainMenuState processMainMenuCommand(KeyType command, MainMenuState mainMenuState) {
        MainMenuState nextMainMenuState;
        switch (command) {
            case ArrowUp:
                nextMainMenuState = MainMenuState.values()[(mainMenuState.ordinal() + 1 + 3) % 3];
                break;
            case ArrowDown:
                nextMainMenuState = MainMenuState.values()[(mainMenuState.ordinal() - 1 + 3) % 3];
                break;
            // case Enter:
            default:
                nextMainMenuState = mainMenuState;
        }
        return nextMainMenuState;
    }

    /**
     * Processing menu commands
     * @param command Input command
     */
    public MenuState processMenuCommand(KeyType command, MenuState menuState) {
        MenuState nextMenuState;
        switch (command) {
            case ArrowUp:
                nextMenuState = MenuState.values()[(menuState.ordinal() + 1 + 3) % 3];
                break;
            case ArrowDown:
                nextMenuState = MenuState.values()[(menuState.ordinal() - 1 + 3) % 3];
                break;
            //  case Enter:
            default:
                nextMenuState = menuState;
        }
        return nextMenuState;
    }

    /**
     * Processing game commands
     */
    /*
    public void processGameCommand(command) {
        switch (command) {
            case LEFT:
                round.movePlayerLeft();
                break;
            case RIGHT:
                round.movePlayerRight();
                break;
            case UP:
                round.movePlayerUp();
                break;
            case DOWN:
                round.movePlayerDown();
                break;
            case CHANGE_EQUIPMENT:
                round.changeEquipment();
                break;
        }
    }*/
}
