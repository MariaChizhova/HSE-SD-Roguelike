package model.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    public final static int INVENTORY_SIZE = 6;
    private final Map<ArtifactName, Artifact> artifacts;
    private final List<ArtifactName> boxes;
    private final List<Boolean> artifactsOn;

    public Inventory() {
        this.artifacts = new HashMap<>();
        this.boxes = new ArrayList<>();
        this.artifactsOn = new ArrayList<>();
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            this.boxes.add(null);
            this.artifactsOn.add(false);
        }
    }

    /**
     * Add artifact to inventory
     * @param artifact will be added if there will be a place in inventory
     * @return if artifact is added
     */
    public boolean addArtifact(Artifact artifact) {
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            if (boxes.get(i) == null && !artifacts.containsKey(artifact.getName())) {
                boxes.set(i, artifact.getName());
                artifacts.put(artifact.getName(), artifact);
                return true;
            }
        }
        return false;
    }

    /**
     * Check the availability of the artifact in inventory
     */
    public boolean checkArtifact(ArtifactName artifactName) {
        return artifacts.containsKey(artifactName);
    }

    /**
     * Get artifacts from inventory
     */
    public List<ArtifactName> getFullInventory() {
        return boxes;
    }

    /**
     * Get th information about artifacts on from inventory
     */
    public List<Boolean> getArtifactsOn() {
        return artifactsOn;
    }

    /**
     * Check if artifact is on
     */
    public boolean checkPutOnArtifact(int i) {
        return artifactsOn.get(i);
    }

    /**
     * Check if artifact is on
     */
    public boolean checkPutOnArtifact(ArtifactName artifactName) {
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            if (boxes.get(i).equals(artifactName)) {
                return artifactsOn.get(i);
            }
        }
        return false;
    }

    /**
     * Put on artifact from inventory if it wasn't or take off it
     * @return removed artifact
     */
    public Artifact putOnTakeOffArtifact(int i) {
        var name = boxes.get(i);
        if (name == null) {
            return null;
        }
        artifactsOn.set(i, !artifactsOn.get(i));
        return artifacts.get(name);
    }
}
