package model;

public class Enemy implements Character, Cell {

    private final static int DEFAULT = 10;
    private final static int maxHealth = 100;
    private int health;
    private int damage;
    private int armor;
    private int experience;
    private Position position;
    private StrategyEnemy strategy;

    public Enemy(Position position, StrategyEnemy strategy) {
        this.health = maxHealth;
        this.damage = DEFAULT;
        this.armor = DEFAULT;
        this.experience = 5;
        this.position = position;
        this.strategy = strategy;
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
        character.beAttacked(this);
    }

    @Override
    public void beAttacked(Character character) {
        health -= character.getDamage() * (1 - (0.06 * armor)/(1 + 0.06 * armor));
    }

    @Override
    public Position move(Position position) {
        Position newPosition = strategy.nextMove(position, this.position);
        this.position = newPosition;
        return newPosition;
    }
}
