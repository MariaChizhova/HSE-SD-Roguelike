package model.inventory;


import java.io.Serializable;

/**
 * Represents all available names of the artifact
 */
public enum ArtifactName implements Serializable {
    HELMET("< Helmet >"),
    CUIRASS("<Cuirass> "),
    GLOVES("< Gloves >"),
    BOOTS("< Boots > "),
    WOODEN_SWORD("<Wooden S>"),
    COPPER_SWORD("<Cooper S>"),
    STEEL_SWORD("<Steel  S>"),
    RAPIER("< Rapier >");

    public String interfaceName;

    ArtifactName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}
