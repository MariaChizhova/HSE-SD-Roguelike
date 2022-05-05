package controller.commands;

import view.ConsoleDrawer;

/**
 * Abstract class for executing commands.
 */
public abstract class Command {
    protected ConsoleDrawer view;

    /**
     * Executes command
     */
    public abstract void execute();
}
