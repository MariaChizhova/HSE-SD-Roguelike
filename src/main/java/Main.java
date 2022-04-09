import view.ConsoleDrawer;
import view.MenuState;

public class Main {
    public static void main(String[] args) {
        var consoleDrawer = new ConsoleDrawer();
        consoleDrawer.drawMenu(MenuState.START);
    }
}
