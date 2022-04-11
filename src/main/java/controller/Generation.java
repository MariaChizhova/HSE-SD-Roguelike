package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Cell;
import model.Enemy;
import model.Field;
import model.GenerationResult;
import model.Player;
import model.Position;
import model.SimpleStrategy;
import model.Wall;
import model.inventory.Artifact;
import model.inventory.ArtifactWithPosition;

import static model.WallDirection.getRandomDirection;
import static model.inventory.Artifact.getArtifactList;

public class Generation {
    public static final int MAX_NUM_OF_ENEMIES = 15;
    public static final int MAX_NUM_OF_ARTIFACTS = 8;
    private Player player;
    private final List<Enemy> enemies = new ArrayList<>();

    private final List<GenerationResult> generation = new ArrayList<>();
    private final Boolean[][] isFilled;

    public Generation() {
        isFilled = new Boolean[Field.FIELD_WIDTH][Field.FIELD_HEIGHT];
        for (int y = 0; y < Field.FIELD_HEIGHT; y++) {
            for (int x = 0; x < Field.FIELD_WIDTH; x++) {
                isFilled[x][y] = false;
            }
        }
        generateWalls();
        generateEnemies();
        generateArtifacts();
        generatePlayer();
    }

    private void generateWalls() {
        for (int y = 1; y < Field.FIELD_HEIGHT; y += 2) {
            for (int x = 1; x < Field.FIELD_WIDTH; x += 2) {
                setCellValue(x, y, new Wall());

                switch (getRandomDirection()) {
                    case UP:
                        if (checkCell(x, y - 1)) {
                            setCellValue(x, y - 1, new Wall());
                        }
                        break;
                    case DOWN:
                        if (checkCell(x, y + 1)) {
                            setCellValue(x, y + 1, new Wall());
                        }
                        break;
                    case RIGHT:
                        if (checkCell(x + 1, y)) {
                            setCellValue(x + 1, y, new Wall());
                        }
                        break;
                    case LEFT:
                        if (checkCell(x - 1, y)) {
                            setCellValue(x - 1, y, new Wall());
                        }
                        break;
                }
            }
        }
    }

    private void generateEnemies() {
        int cnt = 0;
        Random rand = new Random();

        int numOfEnemies = rand.nextInt(MAX_NUM_OF_ENEMIES) + 1;
        while (cnt != numOfEnemies) {
            int enemyXPos = rand.nextInt(Field.FIELD_WIDTH);
            int enemyYPos = rand.nextInt(Field.FIELD_HEIGHT);
            if (!isFilled[enemyXPos][enemyYPos]) {
                var enemy = new Enemy(new Position(enemyXPos, enemyYPos), new SimpleStrategy());
                enemies.add(enemy);
                setCellValue(enemyXPos, enemyYPos, enemy);
                cnt++;
            }
        }
    }

    private void generateArtifacts() {
        int cnt = 0;
        Random rand = new Random();
        var artifactList = getArtifactList();

        int numOfArtifacts = rand.nextInt(MAX_NUM_OF_ARTIFACTS) + 1;
        while (cnt != numOfArtifacts) {
            int xPos = rand.nextInt(Field.FIELD_WIDTH);
            int yPos = rand.nextInt(Field.FIELD_HEIGHT);
            Artifact randomArtifact = artifactList.get(rand.nextInt(artifactList.size()));
            if (!isFilled[xPos][yPos]) {
                var artifact = new ArtifactWithPosition(new Position(xPos, yPos), randomArtifact);
                setCellValue(xPos, yPos, artifact);
                cnt++;
            }
        }
    }

    private void generatePlayer() {
        Random rand = new Random();
        while (true) {
            int playerXPos = rand.nextInt(Field.FIELD_WIDTH);
            int playerYPos = rand.nextInt(Field.FIELD_HEIGHT);
            if (!isFilled[playerXPos][playerYPos]) {
                player = new Player(new Position(playerXPos, playerYPos));
                setCellValue(playerXPos, playerYPos, player);
                break;
            }
        }

    }

    private boolean checkCell(int x, int y) {
        return x >= 0 && y >= 0 && x < Field.FIELD_WIDTH && y < Field.FIELD_HEIGHT;
    }

    private void setCellValue(int x, int y, Cell cell) {
        generation.add(new GenerationResult(x, y, cell));
        isFilled[x][y] = true;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<GenerationResult> getGeneration() {
        return generation;
    }
}
