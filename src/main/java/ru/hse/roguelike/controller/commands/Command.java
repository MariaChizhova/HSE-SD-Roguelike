package ru.hse.roguelike.controller.commands;

import ru.hse.roguelike.view.ConsoleDrawer;

/**
 * Abstract class for executing commands.
 */
public abstract class Command {
    private ConsoleDrawer view;

    protected ConsoleDrawer getView() {
        return view;
    }

    protected void setView(ConsoleDrawer view) {
        this.view = view;
    }

    /**
     * Executes command
     */
    public abstract void execute();
}
