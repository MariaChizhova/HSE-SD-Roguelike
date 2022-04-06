package model;

public class Player implements Character, Cell {

    private final static int DEFAULT = 10;
    private final static int maxHealth = 100;
    private int health;
    private int damage;
    private int armor;
    private int experience;
    private Position position;

    public Player(Position position) {
        this.health = maxHealth;
        this.damage = DEFAULT;
        this.armor = DEFAULT;
        this.experience = 0;
        this.position = position;
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
    public void move(Position newPosition) {
        position = newPosition;
    }
}
