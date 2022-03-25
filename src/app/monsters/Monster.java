package app.monsters;

import app.chars.GameCharacter;

import java.util.List;
import java.util.Random;

public interface Monster extends GameCharacter {

    List<EMonAttType> getAvailableAttacks();

    default EMonAttType getRandomAttack() {
        return getAvailableAttacks().get(
                new Random().nextInt(
                        getAvailableAttacks().size()));
    }
}
