package controller.commands;

import view.ConsoleDrawer;
import view.MenuState;

public class DrawMenuCommand extends Command {

    private MenuState menuState;

    public DrawMenuCommand(ConsoleDrawer view, MenuState menuState) {
        this.view = view;
        this.menuState = menuState;
    }

    @Override
    public void execute() {
        view.drawMenu(menuState);
    }
}
