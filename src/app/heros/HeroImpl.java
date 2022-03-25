package app.heros;

import app.exc.BackpackFullException;
import app.exc.CannotUseWeaponException;
import app.exc.NoWeaponException;
import app.weapons.Weapon;

public abstract class HeroImpl implements Hero {


    private final String NAME;

    private final int MAX_HEALTH;
    private int currHealth;

    private final EHeroClass HERO_CLASS;

    private Weapon[] backpack = new Weapon[2];
    private int backpackIdx = -1;
    private int activeIdx = -1;


    public HeroImpl(int maxHealth, EHeroClass heroClass, String name) {
        this.MAX_HEALTH = maxHealth;
        this.currHealth = MAX_HEALTH;
        this.HERO_CLASS = heroClass;
        this.NAME = name;
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

        try {
            return getActiveWeapon().getDamage();
        } catch (NoWeaponException e) {
            System.err.println(NAME + " has no weapon");
            return 0;
        }
    }


    @Override
    public EHeroClass getHeroClass() {
        return HERO_CLASS;
    }


    @Override
    public void takeWeapon(Weapon weapon) throws BackpackFullException, CannotUseWeaponException {

        if (weapon.getHeroClass() != HERO_CLASS)
            throw new CannotUseWeaponException(HERO_CLASS + " cannot use " + weapon.getWeaponType());

        if (backpackIdx == backpack.length - 1)
            throw new BackpackFullException("Backpack full");

        backpack[++backpackIdx] = weapon;
        activeIdx = backpackIdx;
    }


    @Override
    public Weapon dropActiveWeapon() throws NoWeaponException {

        if (backpackIdx == -1)
            throw new NoWeaponException("No weapons");

        Weapon toRemove = backpack[activeIdx];

        backpackIdx--;
        activeIdx = backpackIdx;

        return toRemove;
    }


    @Override
    public Weapon getActiveWeapon() throws NoWeaponException {

        if (activeIdx == -1)
            throw new NoWeaponException("No weapons");

        return backpack[activeIdx];
    }


    @Override
    public Weapon changeActiveWeapon() throws NoWeaponException {

        if (backpackIdx == -1)
            throw new NoWeaponException("No weapons");

        if (backpackIdx == 0)
            return (backpack[activeIdx]);

        return (backpack[activeIdx = activeIdx == 0 ? 1 : 0]);
    }


    @Override
    public int getBackpackSize() {
        return backpackIdx + 1;
    }


    @Override
    public void printBackpack() {

        System.out.print(NAME + " : ");

        if (backpackIdx == -1) {
            System.out.println("empty\n");
            return;
        }

        for (int i = 0; i <= backpackIdx; i++)
            if (activeIdx == i)
                System.out.print("**" + backpack[i] + " ");
            else
                System.out.print(backpack[i] + " ");

        System.out.println();
    }


    @Override
    public String toString() {
        return NAME;
    }
}
