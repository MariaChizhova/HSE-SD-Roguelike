package ru.hse.roguelike.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.hse.roguelike.model.Cell;
import ru.hse.roguelike.model.GeneratedMap;
import ru.hse.roguelike.model.Player;
import ru.hse.roguelike.model.Position;
import ru.hse.roguelike.model.Wall;
import ru.hse.roguelike.model.enemy.CloneEnemyFactory;
import ru.hse.roguelike.model.enemy.DefaultEnemyFactory;
import ru.hse.roguelike.model.enemy.DragonEnemyFactory;
import ru.hse.roguelike.model.enemy.Enemy;
import ru.hse.roguelike.model.enemy.EnemyFactory;
import ru.hse.roguelike.model.enemy.SkeletonEnemyFactory;
import ru.hse.roguelike.model.inventory.Artifact;
import ru.hse.roguelike.model.inventory.ArtifactWithPosition;
import ru.hse.roguelike.model.inventory.Food;
import ru.hse.roguelike.model.inventory.FoodWithPosition;
import ru.hse.roguelike.model.strategies.StrategyType;

import static ru.hse.roguelike.model.WallDirection.getRandomDirection;
import static ru.hse.roguelike.model.inventory.Artifact.getArtifactList;

/**
 * Random map builder
 **/
public class RandomMapBuilder implements MapBuilder {
    private EnemyFactory enemyFactory = new DefaultEnemyFactory();

    private final int MAX_NUM_OF_PASSIVE_ENEMIES = 2;
    private final int MAX_NUM_OF_TRACKER_ENEMIES = 1;
    private final int MAX_NUM_OF_ARTIFACTS = 8;
    private final int MAX_NUM_OF_FOOD = 8;

    private final int MAX_FOOD_HEAL = 16;
    private final int MIN_FOOD_HEAL = 5;

    private Player player;
    private final ArrayList<Enemy> enemies = new ArrayList<>();

    private final List<GeneratedMap> generation = new ArrayList<>();
    private final Boolean[][] isFilled;
    private int width;
    private int height;

    private Random rand;

    /**
     * Creating MapGenerator instance
     */
    public RandomMapBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        isFilled = new Boolean[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                isFilled[x][y] = false;
            }
        }
        this.rand = new Random();
    }

    /**
     * Builds MapGenerator
     * @return new mapGenerator
     **/
    @Override
    public MapGeneration build() {
        generateWalls();
        generateEnemies();
        generateArtifacts();
        generateFood();
        generatePlayer();
        return new MapGeneration(width, height, player, enemies, generation);
    }

    private void setEnemyFactory(EnemyFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
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
        generateEnemiesDependsOnStrategy(MAX_NUM_OF_PASSIVE_ENEMIES, StrategyType.SIMPLE);
//        generateEnemiesDependsOnStrategy(MAX_NUM_OF_AGGRESSIVE_ENEMIES, StrategyType.AGGRESSIVE);
//        generateEnemiesDependsOnStrategy(MAX_NUM_OF_COWARD_ENEMIES, StrategyType.COWARD);
//        generateEnemiesDependsOnStrategy(MAX_NUM_OF_PATROL_ENEMIES, StrategyType.PATROL);
        generateEnemiesDependsOnStrategy(MAX_NUM_OF_TRACKER_ENEMIES, StrategyType.TRACKER);
    }

    private void generateEnemiesDependsOnStrategy(int maxNum, StrategyType strategyType) {
        int cntOfEnemies = 0;
        int numOfEnemies = rand.nextInt(maxNum) + 1;
        while (cntOfEnemies != numOfEnemies) {
            int enemyXPos = rand.nextInt(width);
            int enemyYPos = rand.nextInt(height);
            if (!isFilled[enemyXPos][enemyYPos]) {
                Enemy enemy;
                int factory = rand.nextInt(4);
                if (factory == 0) {
                    setEnemyFactory(new DefaultEnemyFactory());
                } else if(factory == 1)  {
                    setEnemyFactory(new CloneEnemyFactory());
                } else if(factory == 2) {
                    setEnemyFactory(new SkeletonEnemyFactory());
                } else {
                    setEnemyFactory(new DragonEnemyFactory());
                }
                switch (strategyType) {
                    case AGGRESSIVE:
                        enemy = enemyFactory.createAggressiveEnemy(new Position(enemyXPos, enemyYPos));
                        break;
                    case COWARD:
                        enemy = enemyFactory.createCowardEnemy(new Position(enemyXPos, enemyYPos));
                        break;
                    case PATROL:
                        enemy = enemyFactory.createPatrolEnemy(new Position(enemyXPos, enemyYPos));
                        break;
                    case TRACKER:
                        enemy = enemyFactory.createTrackerEnemy(new Position(enemyXPos, enemyYPos));
                        break;
                    default:
                        enemy = enemyFactory.createPassiveEnemy(new Position(enemyXPos, enemyYPos));
                        break;
                }
                enemies.add(enemy);
                setCellValue(enemyXPos, enemyYPos, enemy);
                cntOfEnemies++;
            }
        }
    }

    private void generateArtifacts() {
        int cntOfArtifacts = 0;
        var artifactList = getArtifactList();

        int numOfArtifacts = rand.nextInt(MAX_NUM_OF_ARTIFACTS) + 1;
        while (cntOfArtifacts != numOfArtifacts) {
            int xPos = rand.nextInt(width);
            int yPos = rand.nextInt(height);
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

        int numOfFood = rand.nextInt(MAX_NUM_OF_FOOD) + 1;
        while (cntOfFood != numOfFood) {
            int xPos = rand.nextInt(width);
            int yPos = rand.nextInt(height);
            Food food = new Food(rand.nextInt(MAX_FOOD_HEAL) + MIN_FOOD_HEAL);
            if (!isFilled[xPos][yPos]) {
                var foodWithPos = new FoodWithPosition(new Position(xPos, yPos), food);
                setCellValue(xPos, yPos, foodWithPos);
                cntOfFood++;
            }
        }
    }

    private void generatePlayer() {
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
        generation.add(new GeneratedMap(x, y, cell));
        isFilled[x][y] = true;
    }
}
