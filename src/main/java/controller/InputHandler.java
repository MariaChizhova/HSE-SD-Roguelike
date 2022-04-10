package controller;

import model.Game;
import model.Move;

public class InputHandler {
    private final Game game;

    /**
     * Creates InputHandler instance
     */
    public InputHandler() {
        this.game = new Game();
    }

    /**
     * Processing main menu commands
     * @param command Input command
     */
    public void processMainMenuCommand(Command command) {
        switch (command) {
            case NEW_GAME:
                game.startGame();
                break;
            case LOAD_GAME:
                game.loadGame();
                break;
            case SAVE_GAME:
                game.saveGame();
                break;
            case EXIT_GAME:
                game.exitGame();
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
                game.makeMove(Move.LEFT);
                break;
            case RIGHT:
                game.makeMove(Move.RIGHT);
                break;
            case UP:
                game.makeMove(Move.UP);
                break;
            case DOWN:
                game.makeMove(Move.DOWN);
                break;
            case CHANGE_EQUIPMENT:
                game.changeEquipment();
                break;
        }
    }
}
