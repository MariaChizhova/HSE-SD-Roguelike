import controller.GameController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var gameController = new GameController();
        gameController.selectMode();
    }
}
