package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import model.Round;
import view.MainMenuState;
import view.MenuState;

/**
 * Responsible for processing of user input
 */
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
     * @return is the game over
     */
    public boolean processGameCommand(KeyType command, Round round) {
        boolean finished = false;
        switch (command) {
            case ArrowLeft:
                finished = round.movePlayerLeft();
                finished |= round.moveEnemies();
                break;
            case ArrowRight:
                finished = round.movePlayerRight();
                finished |= round.moveEnemies();
                break;
            case ArrowUp:
                finished = round.movePlayerUp();
                finished |= round.moveEnemies();
                break;
            case ArrowDown:
                finished = round.movePlayerDown();
                finished |= round.moveEnemies();
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
            case Escape:
                finished = round.moveEnemies();
                break;
        }
        return finished;
    }

    /**
     * Processing game commands
     * @param keyStroke Input character
     * @return the digit
     */
    public Character getNumber(KeyStroke keyStroke) {
        Character character = keyStroke.getCharacter();
        if (Character.isDigit(character)) {
            return character;
        }
        return null;
    }
}
