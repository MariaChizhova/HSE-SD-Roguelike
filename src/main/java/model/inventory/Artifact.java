package model.inventory;

import java.io.Serializable;
import java.util.List;

/**
 * Represents Artifact itself
 */
public class Artifact implements Serializable {

    private final ArtifactName name;
    private final int damage;
    private final int armor;

    /**
     * Creating Artifact instance
     *
     * @param name     Its name
     * @param damage   Its damage
     * @param armor    Its armor
     */
    public Artifact(ArtifactName name, int damage, int armor) {
        this.name = name;
        this.damage = damage;
        this.armor = armor;
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

    public static List<Artifact> getArtifactList() {
        return List.of(
                new Artifact(ArtifactName.WOODEN_SWORD, 20, 0),
                new Artifact(ArtifactName.COPPER_SWORD, 40, 0),
                new Artifact(ArtifactName.STEEL_SWORD, 60, 0),
                new Artifact(ArtifactName.RAPIER, 80, 0),
                new Artifact(ArtifactName.BOOTS, 0, 20),
                new Artifact(ArtifactName.HELMET, 0, 30),
                new Artifact(ArtifactName.CUIRASS, 0, 50),
                new Artifact(ArtifactName.GLOVES, 0, 10)

        );
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "name=" + name +
                ", damage=" + damage +
                ", armor=" + armor +
                '}';
    }
}
