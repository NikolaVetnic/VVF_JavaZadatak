package app.weapons;

import app.heros.EHeroClass;

public class Sword extends WeaponImpl {


    public Sword(String name) {
        super(EHeroClass.SWORDSMAN, EWeaponType.SWORD, name, 10);
    }
}
