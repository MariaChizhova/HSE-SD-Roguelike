package controller;

import model.Round;

import java.io.*;

public class GameSaverLoader {

    private final static String filename = "saved_game.txt";
    /**
     * Saving game to the file
     * @param round The round to save
     */
    public static void saveGame(Round round) throws FileNotFoundException {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(round);
            objectOut.close();
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Loading game from the file
     */
    public static Round loadGame() {
        try {
            FileInputStream fi = new FileInputStream(filename);
            ObjectInputStream oi = new ObjectInputStream(fi);
            Round round = (Round) oi.readObject();
            oi.close();
            fi.close();
            return round;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
