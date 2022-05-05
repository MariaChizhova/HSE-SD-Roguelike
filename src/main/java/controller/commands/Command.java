package controller.commands;

import view.ConsoleDrawer;

public abstract class Command {
    protected ConsoleDrawer view;

    public abstract void execute();
}
