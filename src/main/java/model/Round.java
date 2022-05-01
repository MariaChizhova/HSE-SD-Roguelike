package model;

import model.enemy.Enemy;
import model.inventory.ArtifactWithPosition;
import model.inventory.FoodWithPosition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * This class is responsible for the environment in the round.
 * Regulates the interaction between players and the field.
 */
public class Round implements Serializable {

    private final Player player;
    private ArrayList<Enemy> enemies;
    private final Field field;
    private final static int p = 4;

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
     * @return is the game over
     */
    public boolean movePlayerRight() {
        Position position = player.getPosition();
        return movePlayer(new Position(position.getX() + 1, position.getY()));
    }

    /**
     * Moves the player to the left
     * @return is the game over
     */
    public boolean movePlayerLeft() {
        Position position = player.getPosition();
        return movePlayer(new Position(position.getX() - 1, position.getY()));
    }

    /**
     * Moves the player to the up
     * @return is the game over
     */
    public boolean movePlayerUp() {
        Position position = player.getPosition();
        return movePlayer(new Position(position.getX(), position.getY() - 1));
    }

    /**
     * Moves the player to the down
     * @return is the game over
     */
    public boolean movePlayerDown() {
        Position position = player.getPosition();
        return movePlayer(new Position(position.getX(), position.getY() + 1));
    }


    private boolean movePlayer(Position position) {
        if (field.isInsideBounds(position)) {
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
                return enemies.isEmpty();
            } else if (cell instanceof ArtifactWithPosition) {
                player.addArtifact(((ArtifactWithPosition) cell).getArtifact());
                field.clearCage(position);
            } else if (cell instanceof FoodWithPosition) {
                player.increaseHealth(((FoodWithPosition) cell).getFood().getHealth());
                field.clearCage(position);
            }
        }
        return false;
    }

    /**
     * Moves enemies after players move
     * @return is the game over
     */
    public boolean moveEnemies() {
        ArrayList<Enemy> newEnemies = new ArrayList<>(enemies);
        for (Enemy enemy: enemies) {
            Position oldPosition = enemy.getPosition();
            Position newPosition = enemy.getStrategy().nextMove(player.getPosition(), oldPosition, enemy.getVisibility());
            if (field.isInsideBounds(newPosition)) {
                Cell cell = field.getCell(newPosition);
                if (cell == null || cell instanceof EmptyCell) {
                    enemy.move(newPosition);
                    if (newPosition != oldPosition) {
                        field.clearCage(oldPosition);
                    }
                    field.moveEnemy(newPosition, enemy);

                    if (Objects.equals(enemy.getName(), "ядовитая плесень")) {
                        Random rand = new Random();
                        if (rand.nextInt(p) == 0) {
                            Position positionEnemy = getEmptyCell(newPosition);
                            if (positionEnemy != null) {
                                Enemy newEnemy = enemy.clone(positionEnemy, enemy);
                                newEnemies.add(newEnemy);
                                field.addEnemy(positionEnemy, newEnemy);
                            }
                        }
                    }

                } else if (cell instanceof Player) {
                    enemy.attack((Player) cell);
                    return player.isDead();
                }
            }
        }
        enemies = newEnemies;
        return false;
    }

    public void changeEquipment(int k) {
        player.putOnTakeOffArtifact(k - 1);
    }

    private Position getEmptyCell(Position position) {
        Position positionUp = new Position(position.getX(), position.getY() - 1);
        if (isEmptyPosition(positionUp)) {
            return positionUp;
        }
        Position positionDown = new Position(position.getX(), position.getY() + 1);
        if (isEmptyPosition(positionDown)) {
            return positionDown;
        }
        Position positionRight = new Position(position.getX() + 1, position.getY());
        if (isEmptyPosition(positionRight)) {
            return positionRight;
        }
        Position positionLeft = new Position(position.getX() - 1, position.getY());
        if (isEmptyPosition(positionLeft)) {
            return positionLeft;
        }
        return null;
    }

    private boolean isEmptyPosition(Position position) {
        if (field.isInsideBounds(position)) {
            Cell cell = field.getCell(position);
            return cell == null || cell instanceof EmptyCell;
        }
        return false;
    }

}
