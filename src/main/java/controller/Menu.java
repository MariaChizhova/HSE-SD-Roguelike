package controller;

import model.Game;

public class Menu {

    public Game game;

    public void createNewGame() {
        game.startGame();
    }

    public void createGameFromFile() {
        game.loadGame();
    }

    public void exit() {
        game.exitGame();
    }

}
