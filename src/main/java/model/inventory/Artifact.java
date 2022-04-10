package model.inventory;

import model.Cell;
import model.Position;

/**
 * Represents Artifact itself
 */
public class Artifact implements Cell {

    private final Position position;
    private final ArtifactName name;
    private final int damage;
    private final int armor;

    /**
     * Creating Artifact instance
     *
     * @param position Its position
     * @param name     Its name
     * @param damage   Its damage
     * @param armor    Its armor
     */
    public Artifact(Position position, ArtifactName name, int damage, int armor) {
        this.position = position;
        this.name = name;
        this.damage = damage;
        this.armor = armor;
    }

    /**
     * Getting position of the artifact
     *
     * @return position of the artifact
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Getting armor of the artifact
     *
     * @return armor of the artifact
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Getting damage of the artifact
     *
     * @return damage of the artifact
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Getting name of the artifact
     *
     * @return name of the artifact
     */
    public ArtifactName getName() {
        return name;
    }
}
