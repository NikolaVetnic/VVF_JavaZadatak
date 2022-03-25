package app.monsters;

public class MonsterFactory {


    private static final int SPIDER_HEALTH = 20;
    private static final int DRAGON_HEALTH = 80;


    public static Monster newMonster(EMonType monType, String name) {
        switch (monType) {
            case SPIDER: return new Spider(SPIDER_HEALTH, name);
            case DRAGON: return new Dragon(DRAGON_HEALTH, name);
            default: return null;
        }
    }
}
