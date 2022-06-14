package model;

/**
 * Represents a character, namely a player or an enemy
 */
public interface Character {

    /**
     * Returns character's health
     */
    public int getHealth();

    /**
     * Returns character's experience
     */
    public int getExperience();

    /**
     * Returns character's damage
     */
    public int getDamage();

    /**
     * Returns character's armor
     */
    public int getArmor();

    /**
     * Returns whether the character has died
     */
    public Boolean isDead();

    /**
     * Returns character's position
     */
    public Position getPosition();

    /**
     * An attack is being made on another character
     * @param character who was attacked
     */
    public void attack(Character character);

    /**
     *  Describes what happens when a character is attacked
     * @param character who made the attack
     */
    public void beAttacked(Character character);

    /**
     * The character's position changes
     * @param position depending on the type of character
     * Returns new position
     */
    public Position move(Position position);

}
