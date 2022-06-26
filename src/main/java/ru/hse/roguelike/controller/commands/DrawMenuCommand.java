package ru.hse.roguelike.controller.commands;

import ru.hse.roguelike.view.ConsoleDrawer;
import ru.hse.roguelike.view.MenuState;

/**
 * Represents command that draws menu
 */
public class DrawMenuCommand extends Command {

    private MenuState menuState;

    /**
     * Creates DrawMenuCommand with given arguments
     */
    public DrawMenuCommand(ConsoleDrawer view, MenuState menuState) {
        setView(view);
        this.menuState = menuState;
    }

    /**
     *  Executes DrawMenuCommand
     */
    @Override
    public void execute() {
        getView().drawMenu(menuState);
    }
}
