package app.weapons;

public class WeaponFactory {


    public static Weapon newWeapon(EWeaponType weaponType, String name) {

        switch (weaponType) {
            case SWORD: return new Sword(name);
            case SPEAR: return new Spear(name);
            case SPELL: return new Spell(name);
            default: return null;
        }
    }
}
