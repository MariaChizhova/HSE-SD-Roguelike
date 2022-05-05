package controller.commands;

import model.Field;
import view.ConsoleDrawer;

public class DrawMapCommand extends Command {

    protected Field field;

    public DrawMapCommand(ConsoleDrawer view, Field field) {
        this.view = view;
        this.field = field;
    }

    @Override
    public void execute() {
        view.drawMap(field);
    }
}
