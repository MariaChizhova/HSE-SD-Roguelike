import controller.GameController;
import model.Field;
import view.ConsoleDrawer;
import view.MainMenuState;
import view.MenuState;

public class Main {
    public static void main(String[] args) {
        var consoleDrawer = new ConsoleDrawer();
        var field = new Field(Field.FIELD_WIDTH, Field.FIELD_HEIGHT);
        consoleDrawer.drawMap(field);
        GameController gameController = new GameController(consoleDrawer);

    }
}
