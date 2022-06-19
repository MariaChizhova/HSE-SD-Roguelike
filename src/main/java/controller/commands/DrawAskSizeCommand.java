package controller.commands;

import view.ConsoleDrawer;

/**
 * Represents command that draws menu with field size
 */
public class DrawAskSizeCommand extends Command {

    private Character newWidthChar;
    private Character newHeightChar;
    private boolean isOk;

    private boolean isNew;

    /**
     * Creates DrawAskSizeCommand with given arguments
     */
    public DrawAskSizeCommand(ConsoleDrawer view, Character newWidthChar, Character newHeightChar, boolean isOk, boolean isNew) {
        setView(view);
        this.newWidthChar = newWidthChar;
        this.newHeightChar = newHeightChar;
        this.isOk = isOk;
        this.isNew = isNew;
    }

    /**
     * Executes DrawAskSizeCommand
     */
    @Override
    public void execute() {
        getView().drawAskSize(newWidthChar, newHeightChar, isOk, isNew);
    }
}
