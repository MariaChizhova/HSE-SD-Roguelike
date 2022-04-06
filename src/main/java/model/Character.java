package model;

public interface Character {

    public void decreaseHealth(int count);

    public void increaseHealth(int count);

    public void decreaseDamage(int count);

    public void increaseDamage(int count);

    public void decreaseArmor(int count);

    public void increaseArmor(int count);

    public void increaseExperience(int count);

    public Boolean isDead();

    public Position getPosition();

}
