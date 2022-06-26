package ru.hse.roguelike.controller.commands;

import ru.hse.roguelike.view.ConsoleDrawer;
import ru.hse.roguelike.view.MainMenuState;

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
