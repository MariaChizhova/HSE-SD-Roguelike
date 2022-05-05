package controller.commands;

import view.ConsoleDrawer;
import view.MainMenuState;

public class DrawMainMenuCommand extends Command {
    protected MainMenuState mainMenuState;

    public DrawMainMenuCommand(ConsoleDrawer view, MainMenuState mainMenuState) {
        this.view = view;
        this.mainMenuState = mainMenuState;
    }

    @Override
    public void execute() {
        view.drawMainMenu(mainMenuState);
    }
}
