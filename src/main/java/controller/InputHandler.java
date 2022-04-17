package controller;

import com.googlecode.lanterna.input.KeyType;
import model.Round;
import view.MainMenuState;
import view.MenuState;

public class InputHandler {

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
     * @param command Input command
     */
    public void processGameCommand(KeyType command, Round round) {
        switch (command) {
            case ArrowLeft:
                round.movePlayerLeft();
                break;
            case ArrowRight:
                round.movePlayerRight();
                break;
            case ArrowUp:
                round.movePlayerUp();
                break;
            case ArrowDown:
                round.movePlayerDown();
                break;
            case F1:
                round.changeEquipment(1);
                break;
            case F2:
                round.changeEquipment(2);
                break;
            case F3:
                round.changeEquipment(3);
                break;
            case F4:
                round.changeEquipment(4);
                break;
            case F5:
                round.changeEquipment(5);
                break;
            case F6:
                round.changeEquipment(6);
                break;
        }
    }
}
