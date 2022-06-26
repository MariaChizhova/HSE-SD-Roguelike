package ru.hse.roguelike.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ru.hse.roguelike.model.Cell;
import ru.hse.roguelike.model.GeneratedMap;
import ru.hse.roguelike.model.Player;
import ru.hse.roguelike.model.Position;
import ru.hse.roguelike.model.Wall;
import ru.hse.roguelike.model.enemy.DefaultEnemyFactory;
import ru.hse.roguelike.model.enemy.Enemy;
import ru.hse.roguelike.model.enemy.EnemyFactory;
import ru.hse.roguelike.model.inventory.Artifact;
import ru.hse.roguelike.model.inventory.ArtifactWithPosition;
import ru.hse.roguelike.model.inventory.Food;
import ru.hse.roguelike.model.inventory.FoodWithPosition;

import static ru.hse.roguelike.model.inventory.Artifact.getArtifactList;

/**
 * Map Builder from file
 **/
public class FileMapBuilder implements MapBuilder {
    private EnemyFactory enemyFactory = new DefaultEnemyFactory();

    private Player player;
    private final ArrayList<Enemy> enemies = new ArrayList<>();

    private final List<GeneratedMap> generation = new ArrayList<>();
    private final String fileName;
    private Boolean[][] isFilled;
    private int width;
    private int height;

    private Random rand;

    /**
     * Creating MapGenerator instance
     */
    public FileMapBuilder(String fileName) {
        this.fileName = fileName;
        this.rand = new Random();
    }

    /**
     * Builds MapGenerator
     *
     * @return new mapGenerator
     **/
    @Override
    public MapGeneration build() {
        List<List<String>> fieldText = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            while (in.ready()) {
                String line = in.readLine();
                List<String> characters = Arrays.asList(line.split(""));
                fieldText.add(characters);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.width = fieldText.get(0).size();
        this.height = fieldText.size();
        isFilled = new Boolean[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String ch = fieldText.get(y).get(x);
                if (ch.equals("F")) {
                    Food food = new Food(rand.nextInt(16) + 5);
                    var foodWithPos = new FoodWithPosition(new Position(x, y), food);
                    setCellValue(x, y, foodWithPos);
                } else if (ch.equals("E")) {
                    var enemy = enemyFactory.createAggressiveEnemy(new Position(x, y));
                    enemies.add(enemy);
                    setCellValue(x, y, enemy);
                } else if (ch.equals("S")) {
                    var enemy = enemyFactory.createPassiveEnemy(new Position(x, y));
                    enemies.add(enemy);
                    setCellValue(x, y, enemy);
                } else if (ch.equals("C")) {
                    var enemy = enemyFactory.createCowardEnemy(new Position(x, y));
                    enemies.add(enemy);
                    setCellValue(x, y, enemy);
                } else if (ch.equals("A")) {
                    var artifactList = getArtifactList();
                    Artifact randomArtifact = artifactList.get(rand.nextInt(artifactList.size()));
                    var artifact = new ArtifactWithPosition(new Position(x, y), randomArtifact);
                    setCellValue(x, y, artifact);
                } else if (ch.equals("#")) {
                    setCellValue(x, y, new Wall());
                } else if (ch.equals("P")) {
                    player = new Player(new Position(x, y));
                    setCellValue(x, y, player);
                }
            }
        }
        return new MapGeneration(width, height, player, enemies, generation);
    }

    private void setCellValue(int x, int y, Cell cell) {
        generation.add(new GeneratedMap(x, y, cell));
        isFilled[x][y] = true;
    }
}
