package app.monsters;

import app.exc.NoWeaponException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class MonsterImpl implements Monster {

    private final String NAME;

    private final int MAX_HEALTH;
    private int currHealth;

    private final EMonType MON_TYPE;

    private List<EMonAttType> availableAttacks;


    public MonsterImpl(int maxHealth, EMonType monType, EMonAttType[] availableAttacks, String name) {
        this.NAME = name;
        this.MAX_HEALTH = maxHealth;
        this.currHealth = maxHealth;
        this.MON_TYPE = monType;
        this.availableAttacks = Arrays.stream(availableAttacks).toList();
    }


    @Override
    public String getName() {
        return NAME;
    }


    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }


    @Override
    public int getCurrHealth() {
        return currHealth;
    }


    @Override
    public void takeDamage(int damage) {
        currHealth -= damage;
    }


    @Override
    public boolean isAlive() {
        return currHealth > 0;
    }


    @Override
    public int getDamage() {
        return availableAttacks.get(new Random().nextInt(availableAttacks.size())).getDamage();
    }


    @Override
    public List<EMonAttType> getAvailableAttacks() {
        return availableAttacks;
    }


    @Override
    public String toString() {
        return NAME;
    }
}
