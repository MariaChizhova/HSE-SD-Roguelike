package model.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    public final static int INVENTORY_SIZE = 6;
    private final Map<ArtifactName, Artifact> artifacts;
    private final List<ArtifactName> boxes;

    public Inventory() {
        this.artifacts = new HashMap<>();
        this.boxes = new ArrayList<>();
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            this.boxes.add(null);
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
     * Remove artifact from inventory
     * @return removed artifact
     */
    public Artifact removeArtifact(int i) {
        var name = boxes.get(i);
        boxes.set(i, null);
        if (name == null) {
            return null;
        }
        var artifact = artifacts.get(name);
        artifacts.remove(name);
        return artifact;
    }
}
