package app.weapons;

import app.heros.EHeroClass;

public abstract class WeaponImpl implements Weapon {


    private final EHeroClass HERO_CLASS;
    private final EWeaponType WEAPON_TYPE;

    private final String NAME;

    private final int DAMAGE;


    public WeaponImpl(EHeroClass heroClass, EWeaponType weaponType, String name, int damage) {
        this.HERO_CLASS = heroClass;
        this.WEAPON_TYPE = weaponType;
        this.NAME = name;
        this.DAMAGE = damage;
    }


    public EHeroClass getHeroClass() {
        return HERO_CLASS;
    }


    public EWeaponType getWeaponType() {
        return WEAPON_TYPE;
    }


    public String getWeaponName() {
        return NAME;
    }


    public int getDamage() {
        return DAMAGE;
    }


    @Override public String toString() {
        return NAME + " (dmg : " + DAMAGE + ")";
    }
}
