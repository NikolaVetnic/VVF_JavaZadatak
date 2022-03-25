package app.weapons;

import app.heros.EHeroClass;

public class Spear extends WeaponImpl {


    public Spear(String name) {
        super(EHeroClass.SWORDSMAN, EWeaponType.SPEAR, name, 15);
    }
}
