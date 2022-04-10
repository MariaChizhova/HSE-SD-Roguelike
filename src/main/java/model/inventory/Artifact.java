package model.inventory;

import model.Cell;
import model.Position;

public class Artifact implements Cell {

    private final Position position;
    private final ArtifactName name;
    private final int damage;
    private final int armor;

    public Artifact(Position position, ArtifactName name, int damage, int armor) {
        this.position = position;
        this.name = name;
        this.damage = damage;
        this.armor = armor;
    }

    public Position getPosition() {
        return position;
    }

    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }

    public ArtifactName getName() {
        return name;
    }
}
