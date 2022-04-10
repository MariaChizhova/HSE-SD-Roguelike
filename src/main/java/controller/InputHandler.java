package controller;

import model.Round;

import java.io.FileNotFoundException;

public class InputHandler {
    private final Round round;

    /**
     * Creates InputHandler instance
     */
    public InputHandler(Round round) {
        this.round = round;
    }

    /**
     * Processing main menu commands
     * @param command Input command
     */
    public void processMainMenuCommand(Command command) throws FileNotFoundException {
        switch (command) {
            case NEW_GAME:
                // TODO: run startGame();
                break;
            case LOAD_GAME:
                GameSaverLoader.loadGame(round);
                break;
            case SAVE_GAME:
                GameSaverLoader.saveGame(round);
                break;
            case EXIT_GAME:
                // TODO: run exitGame();
                break;
        }
    }

    /**
     * Processing game commands
     * @param command Input command
     */
    public void processGameCommand(Command command) {
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
    }
}
