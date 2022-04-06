package model;

public interface Character {

    public int getExperience();

    public int getDamage();

    public Boolean isDead();

    public Position getPosition();

    public void attack(Character character);

    public void beAttacked(Character character);

    public void move(Position newPosition);

}
