package app.weapons;

import app.heros.EHeroClass;

public interface Weapon {

    EHeroClass getHeroClass();
    EWeaponType getWeaponType();

    String getWeaponName();

    int getDamage();
}
