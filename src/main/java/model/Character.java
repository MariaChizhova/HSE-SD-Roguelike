package model;

/**
 * Represents a character, namely a player or an enemy
 */
public interface Character {

    /**
     * @return character's health
     */
    public int getHealth();

    /**
     * @return character's experience
     */
    public int getExperience();

    /**
     * @return character's damage
     */
    public int getDamage();

    /**
     * @return character's armor
     */
    public int getArmor();

    /**
     * @return whether the character has died
     */
    public Boolean isDead();

    /**
     * @return character's position
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
     * @return new position
     */
    public Position move(Position position);

}
