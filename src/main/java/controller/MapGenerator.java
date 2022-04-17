package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Cell;
import model.Enemy;
import model.Field;
import model.GeneratedMap;
import model.Player;
import model.Position;
import model.strategies.SimpleStrategy;
import model.Wall;
import model.inventory.Artifact;
import model.inventory.ArtifactWithPosition;
import model.inventory.Food;
import model.inventory.FoodWithPosition;

import static model.WallDirection.getRandomDirection;
import static model.inventory.Artifact.getArtifactList;

/**
 * Random generation of the map
 */
public class MapGenerator {
    private final int MAX_NUM_OF_ENEMIES = 15;
    private final int MAX_NUM_OF_ARTIFACTS = 8;
    private final int MAX_NUM_OF_FOOD = 8;

    private final int MAX_FOOD_HEAL = 16;
    private final int MIN_FOOD_HEAL = 5;

    private Player player;
    private final ArrayList<Enemy> enemies = new ArrayList<>();

    private final List<GeneratedMap> generation = new ArrayList<>();
    private final Boolean[][] isFilled;

    public MapGenerator() {
        isFilled = new Boolean[Field.FIELD_WIDTH][Field.FIELD_HEIGHT];
        for (int y = 0; y < Field.FIELD_HEIGHT; y++) {
            for (int x = 0; x < Field.FIELD_WIDTH; x++) {
                isFilled[x][y] = false;
            }
        }
        generateWalls();
        generateEnemies();
        generateArtifacts();
        generateFood();
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
        int cntOfEnemies = 0;
        Random rand = new Random();

        int numOfEnemies = rand.nextInt(MAX_NUM_OF_ENEMIES) + 1;
        while (cntOfEnemies != numOfEnemies) {
            int enemyXPos = rand.nextInt(Field.FIELD_WIDTH);
            int enemyYPos = rand.nextInt(Field.FIELD_HEIGHT);
            if (!isFilled[enemyXPos][enemyYPos]) {
                var enemy = new Enemy(new Position(enemyXPos, enemyYPos), new SimpleStrategy());
                enemies.add(enemy);
                setCellValue(enemyXPos, enemyYPos, enemy);
                cntOfEnemies++;
            }
        }
    }

    private void generateArtifacts() {
        int cntOfArtifacts = 0;
        Random rand = new Random();
        var artifactList = getArtifactList();

        int numOfArtifacts = rand.nextInt(MAX_NUM_OF_ARTIFACTS) + 1;
        while (cntOfArtifacts != numOfArtifacts) {
            int xPos = rand.nextInt(Field.FIELD_WIDTH);
            int yPos = rand.nextInt(Field.FIELD_HEIGHT);
            Artifact randomArtifact = artifactList.get(rand.nextInt(artifactList.size()));
            if (!isFilled[xPos][yPos]) {
                var artifact = new ArtifactWithPosition(new Position(xPos, yPos), randomArtifact);
                setCellValue(xPos, yPos, artifact);
                cntOfArtifacts++;
            }
        }
    }

    private void generateFood() {
        int cntOfFood = 0;
        Random rand = new Random();

        int numOfFood = rand.nextInt(MAX_NUM_OF_FOOD) + 1;
        while (cntOfFood != numOfFood) {
            int xPos = rand.nextInt(Field.FIELD_WIDTH);
            int yPos = rand.nextInt(Field.FIELD_HEIGHT);
            Food food = new Food(rand.nextInt(MAX_FOOD_HEAL) + MIN_FOOD_HEAL);
            if (!isFilled[xPos][yPos]) {
                var foodWithPos = new FoodWithPosition(new Position(xPos, yPos), food);
                setCellValue(xPos, yPos, foodWithPos);
                cntOfFood++;
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
        generation.add(new GeneratedMap(x, y, cell));
        isFilled[x][y] = true;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public List<GeneratedMap> getGeneration() {
        return generation;
    }
}
