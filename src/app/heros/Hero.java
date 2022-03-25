package app.heros;

import app.exc.BackpackFullException;
import app.exc.CannotUseWeaponException;
import app.exc.NoWeaponException;
import app.chars.GameCharacter;
import app.weapons.Weapon;

public interface Hero extends GameCharacter {

    EHeroClass getHeroClass();

    void takeWeapon(Weapon weapon) throws BackpackFullException, CannotUseWeaponException;
    Weapon dropActiveWeapon() throws NoWeaponException;

    Weapon getActiveWeapon() throws NoWeaponException;
    Weapon changeActiveWeapon() throws NoWeaponException;

    int getBackpackSize();
    void printBackpack();
}
