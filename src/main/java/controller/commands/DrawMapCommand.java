package controller.commands;

import model.Field;
import view.ConsoleDrawer;

/**
 * Represents command that draws field in the terminal
 */
public class DrawMapCommand extends Command {

    private Field field;

    /**
     * Creates DrawMapCommand with given arguments
     */
    public DrawMapCommand(ConsoleDrawer view, Field field) {
        setView(view);
        this.field = field;
    }

    /**
     * Executes DrawMapCommand
     */
    @Override
    public void execute() {
        getView().drawMap(field);
    }
}
