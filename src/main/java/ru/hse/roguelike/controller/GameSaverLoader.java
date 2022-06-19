package ru.hse.roguelike.controller;

import ru.hse.roguelike.model.Round;

import java.io.*;

/**
 * Responsible for saving and loading game
 */
public class GameSaverLoader {

    private final static String filename = "saved_game.bin";
    /**
     * Saving game to the file
     * @param round The round to save
     */
    public static void saveGame(Round round) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(round);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Loading game from the file
     */
    public static Round loadGame() {
        try(FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            return (Round) objectInputStream.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
