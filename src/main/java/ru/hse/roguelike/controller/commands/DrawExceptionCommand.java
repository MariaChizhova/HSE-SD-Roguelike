package ru.hse.roguelike.controller.commands;

import ru.hse.roguelike.view.ConsoleDrawer;

public class DrawExceptionCommand extends Command {

    private String exceptionMsg;

    /**
     * Creates DrawAskSizeCommand with given arguments
     */
    public DrawExceptionCommand(ConsoleDrawer view, String exceptionMsg) {
        setView(view);
        this.exceptionMsg = exceptionMsg;
    }

    /**
     * Executes DrawAskSizeCommand
     */
    @Override
    public void execute() {
        getView().drawException(exceptionMsg);
    }
}
