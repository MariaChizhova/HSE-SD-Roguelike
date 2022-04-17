package controller;

import com.googlecode.lanterna.input.KeyType;
import model.Round;
import view.MainMenuState;
import view.MenuState;

public class InputHandler {

    /**
     * Gives next ordinal number
     * @param value current ordinal number of state
     * @param mod total number of elements in enum with states
     * @return
     */

    private Integer next(Integer value, Integer mod) {
        return (value + 1 + mod) % mod;
    }

    /**
     * Gives previous ordinal number
     * @param value current ordinal number of state
     * @param mod total number of elements in enum with states
     * @return
     */
    private Integer prev(Integer value, Integer mod) {
        return (value - 1 + mod) % mod;
    }

    /**
     * Processing main menu commands
     * @param command Input command
     */
    public MainMenuState processMainMenuCommand(KeyType command, MainMenuState mainMenuState) {
        MainMenuState nextMainMenuState;
        switch (command) {
            case ArrowUp:
                nextMainMenuState =  MainMenuState.values()[prev(mainMenuState.ordinal(), MainMenuState.values().length)];
                break;
            case ArrowDown:
                nextMainMenuState = MainMenuState.values()[next(mainMenuState.ordinal(), MainMenuState.values().length)];
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
                nextMenuState = MenuState.values()[prev(menuState.ordinal(), MenuState.values().length)];
                break;
            case ArrowDown:
                nextMenuState = MenuState.values()[next(menuState.ordinal(), MenuState.values().length)];
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
                round.moveEnemies();
                break;
            case ArrowRight:
                round.movePlayerRight();
                round.moveEnemies();
                break;
            case ArrowUp:
                round.movePlayerUp();
                round.moveEnemies();
                break;
            case ArrowDown:
                round.movePlayerDown();
                round.moveEnemies();
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
