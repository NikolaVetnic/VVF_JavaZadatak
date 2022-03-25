package app.chars;

public interface GameCharacter {

    String getName();

    int getMaxHealth();
    int getCurrHealth();

    int getDamage();

    void takeDamage(int damage);
    boolean isAlive();
}
