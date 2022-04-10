import controller.GameController;
import model.Field;
import view.ConsoleDrawer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var consoleDrawer = new ConsoleDrawer();
        var field = new Field(Field.FIELD_WIDTH, Field.FIELD_HEIGHT);
        consoleDrawer.drawMap(field);
        GameController gameController = new GameController(consoleDrawer);
        gameController.selectMode();
    }
}
