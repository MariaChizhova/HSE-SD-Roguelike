package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Cell;
import model.Enemy;
import model.GenerationResult;
import model.Player;
import model.Position;
import model.strategies.AggressiveStrategy;
import model.strategies.CowardStrategy;
import model.strategies.SimpleStrategy;
import model.Wall;
import model.inventory.Artifact;
import model.inventory.ArtifactWithPosition;
import model.inventory.Food;
import model.inventory.FoodWithPosition;
import model.strategies.StrategyEnemy;

import static model.WallDirection.getRandomDirection;
import static model.inventory.Artifact.getArtifactList;

public class Generation {
    private final int MAX_NUM_OF_AGGRESSIVE_ENEMIES = 3;
    private final int MAX_NUM_OF_PASSIVE_ENEMIES = 5;
    private final int MAX_NUM_OF_COWARD_ENEMIES = 4;
    private final int MAX_NUM_OF_ARTIFACTS = 8;
    private final int MAX_NUM_OF_FOOD = 8;

    private Player player;
    private final ArrayList<Enemy> enemies = new ArrayList<>();

    private final List<GenerationResult> generation = new ArrayList<>();
    private final Boolean[][] isFilled;

    private int width;
    private int height;

    /**
     * Creating Generation instance
     */
    public Generation(int width, int height) {
        this.width = width;
        this.height = height;
        isFilled = new Boolean[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
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
        for (int y = 1; y < height; y += 2) {
            for (int x = 1; x < width; x += 2) {
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
        generateEnemiesDependsOnStrategy(MAX_NUM_OF_PASSIVE_ENEMIES, new SimpleStrategy());
        generateEnemiesDependsOnStrategy(MAX_NUM_OF_AGGRESSIVE_ENEMIES, new AggressiveStrategy());
        generateEnemiesDependsOnStrategy(MAX_NUM_OF_COWARD_ENEMIES, new CowardStrategy());
    }

    private void generateEnemiesDependsOnStrategy(int maxNum, StrategyEnemy strategyEnemy) {
        int cnt = 0;
        Random rand = new Random();

        int numOfEnemies = rand.nextInt(maxNum) + 1;
        while (cnt != numOfEnemies) {
            int enemyXPos = rand.nextInt(width);
            int enemyYPos = rand.nextInt(height);
            if (!isFilled[enemyXPos][enemyYPos]) {
                var enemy = new Enemy(new Position(enemyXPos, enemyYPos), strategyEnemy);
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
            int xPos = rand.nextInt(width);
            int yPos = rand.nextInt(height);
            Artifact randomArtifact = artifactList.get(rand.nextInt(artifactList.size()));
            if (!isFilled[xPos][yPos]) {
                var artifact = new ArtifactWithPosition(new Position(xPos, yPos), randomArtifact);
                setCellValue(xPos, yPos, artifact);
                cnt++;
            }
        }
    }

    private void generateFood() {
        int cnt = 0;
        Random rand = new Random();

        int numOfFood = rand.nextInt(MAX_NUM_OF_FOOD) + 1;
        while (cnt != numOfFood) {
            int xPos = rand.nextInt(width);
            int yPos = rand.nextInt(height);
            Food food = new Food(rand.nextInt(16) + 5);
            if (!isFilled[xPos][yPos]) {
                var foodWithPos = new FoodWithPosition(new Position(xPos, yPos), food);
                setCellValue(xPos, yPos, foodWithPos);
                cnt++;
            }
        }
    }

    private void generatePlayer() {
        Random rand = new Random();
        while (true) {
            int playerXPos = rand.nextInt(width);
            int playerYPos = rand.nextInt(height);
            if (!isFilled[playerXPos][playerYPos]) {
                player = new Player(new Position(playerXPos, playerYPos));
                setCellValue(playerXPos, playerYPos, player);
                break;
            }
        }

    }

    private boolean checkCell(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    private void setCellValue(int x, int y, Cell cell) {
        generation.add(new GenerationResult(x, y, cell));
        isFilled[x][y] = true;
    }


    /**
     * Getting player
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getting all enemies on map
     *
     * @return enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Getting generation of map
     *
     * @return generation
     */
    public List<GenerationResult> getGeneration() {
        return generation;
    }

    /**
     * Getting width of map
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getting width of map
     *
     * @return width
     */
    public int getHeight() {
        return height;
    }
}
