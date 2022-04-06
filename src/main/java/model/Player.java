package model;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Player implements Character, Cell {

    private final static int DEFAULT = 10;
    private final static int maxHealth = 100;
    private int health;
    private int damage;
    private int armor;
    private int experience;
    private Boolean isDead = false;
    private Position position;

    public Player(Position position) {
        this.health = maxHealth;
        this.damage = DEFAULT;
        this.armor = DEFAULT;
        this.experience = 0;
        this.position = position;
    }

    @Override
    public void decreaseHealth(int count) {
        health -= count;
        if (health <= 0) {
            isDead = true;
        }
    }

    @Override
    public void increaseHealth(int count) {
        health = min(maxHealth, health + count);
    }

    @Override
    public void decreaseDamage(int count) {
        damage = max(damage - count, 0);
    }

    @Override
    public void increaseDamage(int count) {
        damage += count;
    }

    @Override
    public void decreaseArmor(int count) {
        armor = max(armor - count, 0);
    }

    @Override
    public void increaseArmor(int count) {
        armor += count;
    }

    @Override
    public void increaseExperience(int count) {
        experience += count;
    }

    @Override
    public Boolean isDead() {
        return isDead;
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
