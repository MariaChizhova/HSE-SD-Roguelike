package model.inventory;

import model.Cell;
import model.Position;

/**
 * Represents Artifact itself
 */
public class ArtifactWithPosition implements Cell {

    private final Position position;
    private final Artifact artifact;

    /**
     * Creating ArtifactWithPosition instance
     *
     * @param position Its position
     * @param artifact Artifact itself
     */
    public ArtifactWithPosition(Position position, Artifact artifact) {
        this.position = position;
        this.artifact = artifact;
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
     * Getting artifact
     *
     * @return artifact
     */
    public Artifact getArtifact() {
        return artifact;
    }

}
