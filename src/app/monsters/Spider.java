package app.monsters;

public class Spider extends MonsterImpl {

    public Spider(int maxHealth, String name) {
        super(maxHealth, EMonType.SPIDER, new EMonAttType[] { EMonAttType.STRIKE, EMonAttType.BITE }, name);
    }
}
