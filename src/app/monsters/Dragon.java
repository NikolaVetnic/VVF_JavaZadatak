package app.monsters;

public class Dragon extends MonsterImpl {

    public Dragon(int maxHealth, String name) {
        super(maxHealth, EMonType.DRAGON, new EMonAttType[] { EMonAttType.STRIKE, EMonAttType.FIRE_BREATH }, name);
    }
}
