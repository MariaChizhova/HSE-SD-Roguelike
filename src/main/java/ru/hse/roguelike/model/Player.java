package ru.hse.roguelike.model;

import ru.hse.roguelike.model.inventory.Artifact;
import ru.hse.roguelike.model.inventory.ArtifactName;
import ru.hse.roguelike.model.inventory.Inventory;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a player who is a character and who stands on some cell of the field
 */
public class Player implements Character, Cell, Serializable {

    private final static int DEFAULT = 10;
    private final static int maxHealth = 100;
    private final static int IS_NEXT_LEVEL = 20;
    private int health;
    private int damage;
    private int armor;
    private int experience;
    private Position position;
    private final Inventory inventory;
    private int level;

    /**
     * Player Constructor
     * @param position the player is in
     */
    public Player(Position position) {
        this.health = maxHealth;
        this.damage = DEFAULT;
        this.armor = DEFAULT;
        this.experience = 0;
        this.position = position;
        this.inventory = new Inventory();
        this.level = 0;
    }

    /**
     * @return the player's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return the player's experience
     */
    @Override
    public int getExperience() {
        return experience;
    }

    /**
     * @return the player's damage
     */
    @Override
    public int getDamage() {
        return damage;
    }

    /**
     * @return the player's armor
     */
    @Override
    public int getArmor() {
        return armor;
    }

    /**
     * @return the player died
     */
    @Override
    public Boolean isDead() {
        return health <= 0;
    }

    /**
     * @return the player's position
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Enemy experience is added
     * @param character who was attacked
     */
    @Override
    public void attack(Character character) {
        character.beAttacked(this);
        if (character.isDead()) {
            experience += character.getExperience();
            increaseLevel();
        }
    }

    /**
     * Health decreases when attacking a player
     * @param character who made the attack
     */
    @Override
    public void beAttacked(Character character) {
        health -= character.getDamage() * (1 - (0.06 * armor)/(1 + 0.06 * armor));
    }

    /**
     * @param position to which the player will move
     * @return new position
     */
    @Override
    public Position move(Position position) {
        this.position = position;
        return position;
    }

    /**
     * Increase health
     * @param count - how much to increase health
     */
    public void increaseHealth(int count) {
        health += count;
    }

    /**
     * Add an artifact
     */
    public void addArtifact(Artifact artifact) {
        inventory.addArtifact(artifact);
    }

    /**
     * Check the availability of the artifact
     */
    public boolean hasArtifact(ArtifactName artifactName) {
        return inventory.checkArtifact(artifactName);
    }

    /**
     * Check the if artifact is on
     */
    public boolean isArtifactOn(ArtifactName artifactName) {
        return inventory.checkPutOnArtifact(artifactName);
    }

    /**
     * Check the availability of the artifact
     */
    public List<ArtifactName> getInventory() {
        return inventory.getFullInventory();
    }

    /**
     * Check the artifacts on from inventory
     */
    public List<Boolean> getArtifactsOn() {
        return inventory.getArtifactsOn();
    }

    private void increaseLevel() {
        if (experience >= IS_NEXT_LEVEL) {
            level += 1;
            experience = 0;
            growthOfCharacteristics();
        }
    }

    private void growthOfCharacteristics() {
        damage += 1;
        armor += 1;
        health = maxHealth;
    }

    /**
     * @return the player's level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Put on artifact from inventory if it wasn't and take off otherwise
     */
    public void putOnTakeOffArtifact(int i) {
        var artifact = inventory.putOnTakeOffArtifact(i);
        if (artifact != null) {
            boolean isOn = inventory.checkPutOnArtifact(i);
            var sign = isOn ? 1 : -1;
            damage += sign * artifact.getDamage();
            armor += sign * artifact.getArmor();
        }
    }
}
