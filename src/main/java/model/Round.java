package model;

import model.inventory.Artifact;
import model.inventory.Food;

import java.util.List;

/**
 * This class is responsible for the environment in the round.
 * Regulates the interaction between players and the field.
 */
public class Round {

    private final Player player;
    private final List<Enemy> enemies;
    private final Field field;

    /**
     * Creating Round instance
     * @param player Its player
     * @param enemies Its enemies
     * @param field Its filed
     */
    public Round(Player player, List<Enemy> enemies, Field field) {
        this.player = player;
        this.enemies = enemies;
        this.field = field;
    }

    /**
     * Moves the player to the right
     */
    public void movePlayerRight() {
        Position position = player.getPosition();
        movePlayer(new Position(position.getX() + 1, position.getY()));
    }

    /**
     * Moves the player to the left
     */
    public void movePlayerLeft() {
        Position position = player.getPosition();
        movePlayer(new Position(position.getX() - 1, position.getY()));
    }

    /**
     * Moves the player to the up
     */
    public void movePlayerUp() {
        Position position = player.getPosition();
        movePlayer(new Position(position.getX(), position.getY() - 1));
    }

    /**
     * Moves the player to the down
     */
    public void movePlayerDown() {
        Position position = player.getPosition();
        movePlayer(new Position(position.getX(), position.getY() + 1));
    }

    private void movePlayer(Position position) {
        if (field.isValidPosition(position)) {
            Cell cell = field.getCell(position);
            if (cell == null) {
                field.movePlayer(position, player);
                player.move(position);
            } else if (cell instanceof Enemy) {
                player.attack((Enemy) cell);
                if (((Enemy) cell).isDead()) {
                    field.movePlayer(position, player);
                    player.move(position);
                }
            } else if (cell instanceof Artifact) {
                player.addArtifact( (Artifact) cell);
                field.clearCage(position);
            } else if (cell instanceof Food) {
                player.increaseHealth(((Food) cell).getHealth());
                field.clearCage(position);
            }
        }
    }

    private void moveEnemies() {
        for (Enemy enemy: enemies) {
            Position newPosition = enemy.move(player.getPosition());
            field.moveEnemy(newPosition, enemy);
        }
    }

    public void changeEquipment() {
        // TODO:
    }

}
