package model;

import model.inventory.ArtifactWithPosition;
import model.inventory.FoodWithPosition;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is responsible for the environment in the round.
 * Regulates the interaction between players and the field.
 */
public class Round implements Serializable {

    private final Player player;
    private final ArrayList<Enemy> enemies;
    private final Field field;

    /**
     * Creating Round instance
     * @param player Its player
     * @param enemies Its enemies
     * @param field Its filed
     */
    public Round(Player player, ArrayList<Enemy> enemies, Field field) {
        this.player = player;
        this.enemies = enemies;
        this.field = field;
    }

    /**
     * Getting Player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getting field
     * @return field
     */
    public Field getField() {
        return field;
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
            if (cell == null || cell instanceof EmptyCell) {
                field.movePlayer(position, player);
                player.move(position);
            } else if (cell instanceof Enemy) {
                player.attack((Enemy) cell);
                if (((Enemy) cell).isDead()) {
                    enemies.remove((Enemy) cell);
                    field.movePlayer(position, player);
                    player.move(position);
                }
            } else if (cell instanceof ArtifactWithPosition) {
                player.addArtifact(((ArtifactWithPosition) cell).getArtifact());
                field.clearCage(position);
            } else if (cell instanceof FoodWithPosition) {
                player.increaseHealth(((FoodWithPosition) cell).getFood().getHealth());
                field.clearCage(position);
            }
        }
    }

    /**
     * Moves enemies after players move
     */
    public void moveEnemies() {
        for (Enemy enemy: enemies) {
            Position oldPosition = enemy.getPosition();
            Position newPosition = enemy.getStrategy().nextMove(player.getPosition(), oldPosition, enemy.getVisibility());
            if (field.isValidPosition(newPosition)) {
                Cell cell = field.getCell(newPosition);
                if (cell == null || cell instanceof EmptyCell) {
                    enemy.move(newPosition);
                    if (newPosition != oldPosition) {
                        field.clearCage(oldPosition);
                    }
                    field.moveEnemy(newPosition, enemy);
                } else if (cell instanceof Player) {
                    enemy.attack((Player) cell);
                    // todo if player is dead
                }
            }


        }
    }

    public void changeEquipment() {
        // TODO:
    }

}
