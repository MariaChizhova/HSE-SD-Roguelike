package controller.commands;

import view.ConsoleDrawer;

/**
 * Represents command that draws menu with field size
 */
public class DrawAskSizeCommand extends Command {

    private Character newWidthChar;
    private Character newHeightChar;
    private boolean isOk;

    /**
     * Creates DrawAskSizeCommand with given arguments
     */
    public DrawAskSizeCommand(ConsoleDrawer view, Character newWidthChar, Character newHeightChar, boolean isOk) {
        this.view = view;
        this.newWidthChar = newWidthChar;
        this.newHeightChar = newHeightChar;
        this.isOk = isOk;
    }

    /**
     * Executes DrawAskSizeCommand
     */
    @Override
    public void execute() {
        view.drawAskSize(newWidthChar, newHeightChar, isOk);
    }
}
