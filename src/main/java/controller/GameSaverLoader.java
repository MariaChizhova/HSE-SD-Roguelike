package controller;

import model.Round;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GameSaverLoader {

    private final static String filename = "saved_game.txt";
    /**
     * Saving game to the file
     * @param round The round to save
     */
    public static void saveGame(Round round) throws FileNotFoundException {
        File file = new File(filename);
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
        // TODO: save game
    }

    /**
     * Loading game from the file
     * @param round The round to save
     */
    public static Round loadGame(Round round) {
        File file = new File(filename);
        if (!file.exists()) {
            return null;
        }
        // TODO: load game
        return null;
    }
}
