package app;

import app.exc.BackpackFullException;
import app.exc.CannotUseWeaponException;
import app.exc.NoWeaponException;
import app.heros.EHeroClass;
import app.heros.Hero;
import app.heros.HeroFactory;
import app.monsters.EMonAttType;
import app.monsters.EMonType;
import app.monsters.Monster;
import app.monsters.MonsterFactory;
import app.weapons.EWeaponType;
import app.weapons.Weapon;
import app.weapons.WeaponFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

public class Arena {


    private final String[] HERO_NAMES = { "Armin", "Eren", "Velekh", "Erna", "Edgar", "Oberon", "Veleszar", "Ylva" };

    private final String[] SWORD_NAMES = { "Sword of Night and Flame", "Drake's Sword", "Zweihaender" };
    private final String[] SPEAR_NAMES = { "Penetrator", "The Piercer", "Winged Spear" };
    private final String[] SPELL_NAMES = { "Soul Arrow", "Magic Missle", "Fireball" };

    private final String[] SPIDER_NAMES = { "Frost Spider", "Common Spider", "Poisonous Spider" };
    private final String[] DRAGON_NAMES = { "Ancient Dragon", "Undead Dragon", "Blight Dragon" };


    private Map<EHeroClass, List<Weapon>> availableWeapons;
    private List<Hero> heroes;
    private List<Monster> monsters;

    private List<String> log;


    public Arena(int numHeroes, int numSpiders, int numDragons) {

        this.heroes = new ArrayList<>();
        this.monsters = new ArrayList<>();

        this.availableWeapons = new HashMap<>();

        this.availableWeapons.put(EHeroClass.SWORDSMAN, new ArrayList<>());
        this.availableWeapons.put(EHeroClass.SORCERER, new ArrayList<>());

        addHeroes(numHeroes);
        addMonsters(numSpiders, numDragons);
        addWeapons();

        this.log = new ArrayList<>();
    }

    private void addMonsters(int numSpiders, int numDragons) {

        if (numSpiders > SPIDER_NAMES.length)
            throw new IllegalArgumentException("Not enough spider names");

        if (numDragons > DRAGON_NAMES.length)
            throw new IllegalArgumentException("Not enough dragon names");

        for (int i = 0; i < numSpiders; i++)
            monsters.add(MonsterFactory.newMonster(EMonType.SPIDER, SPIDER_NAMES[i]));

        for (int i = 0; i < numDragons; i++)
            monsters.add(MonsterFactory.newMonster(EMonType.DRAGON, DRAGON_NAMES[i]));
    }

    private void addHeroes(int numHeroes) {

        if (numHeroes > HERO_NAMES.length)
            throw new IllegalArgumentException("Not enough hero names");

        for (int i = 0; i < numHeroes; i++)
            heroes.add(HeroFactory.newHero(HERO_NAMES[i],
                    new Random().nextDouble() < 0.67 ? EHeroClass.SWORDSMAN : EHeroClass.SORCERER));
    }


    private void addWeapons() {

        for (int i = 0; i < SWORD_NAMES.length; i++)
            availableWeapons.get(EHeroClass.SWORDSMAN).add(WeaponFactory.newWeapon(EWeaponType.SWORD, SWORD_NAMES[i]));

        for (int i = 0; i < SPEAR_NAMES.length; i++)
            availableWeapons.get(EHeroClass.SWORDSMAN).add(WeaponFactory.newWeapon(EWeaponType.SPEAR, SPEAR_NAMES[i]));

        for (int i = 0; i < SPELL_NAMES.length; i++)
            availableWeapons.get(EHeroClass.SORCERER).add(WeaponFactory.newWeapon(EWeaponType.SPELL, SPELL_NAMES[i]));
    }


    public Weapon removeAvailableWeapon(Hero hero) throws NoWeaponException {

        if (availableWeapons.get(hero.getHeroClass()).isEmpty())
            throw new NoWeaponException("No available weapons");

        return availableWeapons.get(hero.getHeroClass()).remove(0);
    }


    public void addAvailableWeapon(Weapon weapon) {
        availableWeapons.get(weapon.getHeroClass()).add(weapon);
    }


    public void printHeroes() {
        System.out.println("HEROES : " + heroes + "\n");
    }


    public void printMonsters() {
        System.out.println("MONSTERS : " + monsters + "\n");
    }


    public void printAvailableWeapons() {

        System.out.print("SWORDSMAN : " + availableWeapons.get(EHeroClass.SWORDSMAN) + "\n");
        System.out.print("SORCERER : " + availableWeapons.get(EHeroClass.SORCERER) + "\n");
    }


    public void startSimulation() {

        int cnt = 0;

        log.add("Heroji : " + heroes);
        log.add("Cudovista : " + monsters);
        log.add("Hladna oruzja : " + availableWeapons.get(EHeroClass.SWORDSMAN));
        log.add("Carolije : " + availableWeapons.get(EHeroClass.SORCERER));
        log.add(" ");

        log.add("POCETAK bitke");
        log.add(" ");

        while (!heroes.isEmpty() && !monsters.isEmpty()) {

            cnt++;

            // taking & dropping weapons
            for (Hero h : heroes) {

                if (h.getBackpackSize() == 2 && new Random().nextDouble() < 0.5) {          // if bp full maybe drops
                    try {

                        Weapon w = h.dropActiveWeapon();

                        addAvailableWeapon(w);
                        log.add(h.getName() + " je bacio oruzje " + w.getWeaponName());

                    } catch (NoWeaponException e) {
                        System.err.println(e.getMessage());
                        log.add(h.getName() + " je pokusao da baci oruzje ali nije uspeo");
                    }
                } else if (new Random().nextDouble() < 0.5 || h.getBackpackSize() == 0) {   // if bp empty or rolls successfully takes
                    try {

                        Weapon w = removeAvailableWeapon(h);

                        h.takeWeapon(w);
                        log.add(h.getName() + " je pokupio oruzje " + w.getWeaponName());

                    } catch (BackpackFullException | CannotUseWeaponException |NoWeaponException e) {
                        System.err.println(e.getMessage());
                        log.add(h.getName() + " je pokusao da pokupi oruzje ali nije uspeo");
                    }
                }
            }

            // heroes attack
            for (Hero h : heroes) {

                if (monsters.isEmpty())
                    continue;

                try {

                    Monster target = monsters.get(new Random().nextInt(monsters.size()));

                    int dmg = h.getDamage();
                    target.takeDamage(dmg);

                    log.add(h.getName() + " je napao " + target.getName() + " pomocu " + h.getActiveWeapon());

                    if (!target.isAlive()) {
                        monsters.remove(target);
                        log.add(h.getName() + " JE POBEDIO u duelu sa " + target.getName());
                    }

                } catch (NoWeaponException e) {
                    System.err.println(h.getName() + " --> " + e.getMessage());
                    log.add(h.getName() + " je pokusao napad ali nema oruzja");
                }
            }

            // monsters attack
            for (Monster m : monsters) {

                if (heroes.isEmpty())
                    continue;

                Hero target = heroes.get(new Random().nextInt(heroes.size()));

                EMonAttType at = m.getRandomAttack();
                int dmg = at.getDamage();
                target.takeDamage(dmg);

                log.add(m.getName() + " je napao " + target.getName() + " pomocu " + at.toString() + " (dmg : " + at.getDamage() + ")");

                if (!target.isAlive()) {
                    heroes.remove(target);
                    log.add(m.getName() + " JE POBEDIO u duelu sa " + target.getName());
                }
            }

            log.add("\tKRAJ " + cnt + ". poteza");
        }

        log.add("\tPobedili su : " + (heroes.isEmpty() ? "CUDOVISTA" : "HEROJI"));

        saveLog("log");
    }


    private void saveLog(String fileName) {

        try {

            PrintWriter pw = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter("logs/" + fileName + ".txt")));

            log.stream().forEach(s -> pw.write(s + "\n"));

            pw.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
