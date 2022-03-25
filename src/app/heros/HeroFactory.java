package app.heros;

public class HeroFactory {


    public static Hero newHero(String name, EHeroClass heroClass) {
        if (heroClass == EHeroClass.SWORDSMAN)
            return new Swordsman(name);
        else
            return new Sorcerer(name);
    }
}
