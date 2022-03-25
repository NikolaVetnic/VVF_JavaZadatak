package app.monsters;

public enum EMonAttType {

    STRIKE(5),
    FIRE_BREATH(20),
    BITE(8);

    int dmg;

    private EMonAttType(int dmg) {
        this.dmg = dmg;
    }

    public int getDamage() { return dmg; }
}
