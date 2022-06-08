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
            case Escape:
                round.changeEquipment();
                break;
        }
    }
}
