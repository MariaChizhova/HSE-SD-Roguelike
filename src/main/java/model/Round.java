package model;

import java.util.List;

public class Round {

    private final Player player;
    private final List<Enemy> enemies;
    private final Field field;

    public Round(Player player, List<Enemy> enemies, Field field) {
        this.player = player;
        this.enemies = enemies;
        this.field = field;
    }

    public void movePlayerRight() {
        Position position = player.getPosition();
        movePlayer(new Position(position.getX() + 1, position.getY()));
    }

    public void movePlayerLeft() {
        Position position = player.getPosition();
        movePlayer(new Position(position.getX() - 1, position.getY()));
    }

    public void movePlayerUp() {
        Position position = player.getPosition();
        movePlayer(new Position(position.getX(), position.getY() - 1));
    }

    public void movePlayerDown() {
        Position position = player.getPosition();
        movePlayer(new Position(position.getX(), position.getY() + 1));
    }

    private void movePlayer(Position position) {
        if (field.isValidPosition(position)) {
            Cell cell = field.getCell(position);
            if (cell instanceof EmptyCell) {
                field.movePlayer(position, player);
                player.move(position);
            } else if (cell instanceof Enemy) {
                player.attack((Enemy) cell);
                if (((Enemy) cell).isDead()) {
                    field.movePlayer(position, player);
                    player.move(position);
                }
            } // если предмет лежит
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
