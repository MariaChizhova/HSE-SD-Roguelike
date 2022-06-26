package ru.hse.roguelike.controller;

import java.util.List;

import ru.hse.roguelike.model.GeneratedMap;
import ru.hse.roguelike.model.Player;
import ru.hse.roguelike.model.enemy.Enemy;

/**
 * Random generation of the map
 */
public class MapGeneration {

    private Player player;
    private List<Enemy> enemies;
    private List<GeneratedMap> generation;
    private int width;
    private int height;

    /**
     * Creating MapGenerator instance
     */
    public MapGeneration(int width, int height, Player player, List<Enemy> enemies, List<GeneratedMap> generation) {
        this.width = width;
        this.height = height;
        this.player = player;
        this.enemies = enemies;
        this.generation = generation;
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
    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Getting generation of map
     *
     * @return generation
     */
    public List<GeneratedMap> getGeneration() {
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
