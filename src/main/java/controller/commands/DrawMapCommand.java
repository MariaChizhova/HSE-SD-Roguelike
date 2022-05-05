package controller.commands;

import model.Field;
import view.ConsoleDrawer;

/**
 * Represents command that draws field in the terminal
 */
public class DrawMapCommand extends Command {

    protected Field field;

    /**
     * Creates DrawMapCommand with given arguments
     */
    public DrawMapCommand(ConsoleDrawer view, Field field) {
        this.view = view;
        this.field = field;
    }

    /**
     * Executes DrawMapCommand
     */
    @Override
    public void execute() {
        view.drawMap(field);
    }
}
