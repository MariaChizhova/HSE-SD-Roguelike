import model.Field;
import view.ConsoleDrawer;

public class Main {
    public static void main(String[] args) {
        var consoleDrawer = new ConsoleDrawer();
        var field = new Field(Field.FIELD_WIDTH, Field.FIELD_HEIGHT);
        consoleDrawer.drawMap(field);
    }
}
