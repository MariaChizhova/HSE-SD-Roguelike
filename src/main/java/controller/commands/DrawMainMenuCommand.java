package controller.commands;

import view.ConsoleDrawer;
import view.MainMenuState;

/**
 * Represents command that draws menu in the terminal
 */
public class DrawMainMenuCommand extends Command {
    protected MainMenuState mainMenuState;

    /**
     * Creates DrawMainMenuCommand with given arguments
     */
    public DrawMainMenuCommand(ConsoleDrawer view, MainMenuState mainMenuState) {
        setView(view);
        this.mainMenuState = mainMenuState;
    }

    /**
     *  Executes DrawMainMenuCommand
     */
    @Override
    public void execute() {
        getView().drawMainMenu(mainMenuState);
    }
}
