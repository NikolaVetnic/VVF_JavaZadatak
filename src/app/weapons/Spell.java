package app.weapons;

import app.heros.EHeroClass;

public class Spell extends WeaponImpl {


    public Spell(String name) {
        super(EHeroClass.SORCERER, EWeaponType.SPELL, name, 20);
    }
}
