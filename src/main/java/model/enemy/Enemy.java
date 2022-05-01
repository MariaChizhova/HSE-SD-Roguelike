package model.enemy;

import model.Cell;
import model.Character;
import model.Position;
import model.strategies.StrategyEnemy;

import java.io.Serializable;

/**
 * Represents enemy who is a character and who stands on some square of the field
 */
public class Enemy implements Character, Cell, Serializable {

    private final static int DEFAULT = 5;
    private final static int maxHealth = 100;
    private int health;
    private int damage;
    private int armor;
    private int experience;
    private int visibility;
    private Position position;
    private StrategyEnemy strategy;
    private String name;

    /**
     * Enemy Constructor
     * @param position the enemy is in
     * @param strategy that the enemy will follow
     */
    public Enemy(Position position, StrategyEnemy strategy, String name) {
        this.health = maxHealth;
        this.damage = DEFAULT;
        this.armor = DEFAULT;
        this.experience = 5;
        this.visibility = 5;
        this.position = position;
        this.strategy = strategy;
        this.name = name;
    }

    /**
     * @return enemy health
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * @return the experience that the player will get when killing this enemy
     */
    @Override
    public int getExperience() {
        return experience;
    }

    /**
     * @return enemy damage
     */
    @Override
    public int getDamage() {
        return damage;
    }

    /**
     * @return enemy armor
     */
    @Override
    public int getArmor() {
        return armor;
    }

    /**
     * @return is the enemy dead
     */
    @Override
    public Boolean isDead() {
        return health <= 0;
    }

    /**
     * @return enemy position
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * @param character who was attacked
     */
    @Override
    public void attack(Character character) {
        character.beAttacked(this);
    }

    /**
     * Health decreases when attacking enemy
     * @param character who made the attack
     */
    @Override
    public void beAttacked(Character character) {
        health -= character.getDamage() * (1 - (0.06 * armor)/(1 + 0.06 * armor));
    }

    /**
     * The enemy changes its position depending on the strategy of moving across the field
     * @param position - player's position
     * @return new position
     */
    @Override
    public Position move(Position position) {
        this.position = position;
        return position;
    }

    /**
     *
     * @return enemy strategy
     */
    public StrategyEnemy getStrategy() {
        return strategy;
    }

    /**
     * @return visibility
     */
    public int getVisibility() {
        return visibility;
    }

    /**
     * @param position for clone of enemy
     * @return clone of enemy
     */
    public Enemy clone(Position position, String name) {
        return new Enemy(position, strategy, name);
    }

    /**
     * @return enemy name
     */
    public String getName() {
        return name;
    }
}
