import controller.GameController;
import view.ConsoleDrawer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var consoleDrawer = new ConsoleDrawer();
        GameController gameController = new GameController(consoleDrawer);
        gameController.selectMode();
    }
}
