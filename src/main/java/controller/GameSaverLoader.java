package controller;

import model.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GameSaverLoader {
    // Почему у нас тут отдельный файл, может это внутри Game?

    private final static String filename = "saved_game.txt";
    /**
     * Saving game to the file
     * @param game The game to save
     */
    public void saveGame(Game game) throws FileNotFoundException {
        File file = new File(filename);
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
        // TODO: save game
    }

    /**
     * Loading game from the file
     * @param game The game to save
     */
    public Game loadGame(Game game) {
        File file = new File(filename);
        if (!file.exists()) {
            return null;
        }
        Game loadedGame = new Game();
        // TODO: load game
        return loadedGame;
    }
}
