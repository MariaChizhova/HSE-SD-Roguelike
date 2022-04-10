package model;

import model.inventory.Artifact;

import java.util.ArrayList;
import java.util.List;

public class Player implements Character, Cell {

    private final static int DEFAULT = 10;
    private final static int maxHealth = 100;
    private int health;
    private int damage;
    private int armor;
    private int experience;
    private Position position;
    private List<Artifact> artifacts;

    public Player(Position position) {
        this.health = maxHealth;
        this.damage = DEFAULT;
        this.armor = DEFAULT;
        this.experience = 0;
        this.position = position;
        this.artifacts = new ArrayList<>();
    }

    public int getHealth() {
        return health;
    }

    @Override
    public int getExperience() {
        return experience;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public Boolean isDead() {
        return health <= 0;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void attack(Character character) {
        experience += character.getExperience();
        character.beAttacked(this);
    }

    @Override
    public void beAttacked(Character character) {
        health -= character.getDamage() * (1 - (0.06 * armor)/(1 + 0.06 * armor));
    }

    @Override
    public Position move(Position position) {
        this.position = position;
        return position;
    }

    public void increaseHealth(int count) {
        health += count;
    }

    public void addArtifact(Artifact artifact) {
        artifacts.add(artifact);
    }
}
