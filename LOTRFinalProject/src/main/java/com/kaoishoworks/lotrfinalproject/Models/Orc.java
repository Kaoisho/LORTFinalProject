package com.kaoishoworks.lotrfinalproject.Models;
/**
 * Esta clase define los parametros que tiene una bestia de raza orco.
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */
public final class Orc extends Beasts {
    public Orc(String name, int hp, int armor) {
        super(name, hp, armor);
        setCurrentHp(getDEFAULT_HP());
        setRace("Orco");
    }
}
