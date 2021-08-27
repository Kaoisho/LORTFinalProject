package com.kaoishoworks.lotrfinalproject.Models;

/**
 * Esta clase define los parametros que tiene un héroe de raza humano.
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */
public final class Human extends Heroes {

    public Human (String name, int hp, int armor) {
        super(name, hp, armor);
        setCurrentHp(getDEFAULT_HP());
        setRace("Humano");
    }
    /**
     * Método que llamará a las habilidades de los heroes aplicando bonificadores y penalizadores si procede.
     * @param beast bestia que le aplica el modificador al héroe
     */
    @Override
    public void applyModifiers(Beasts beast) {
        reduceArmor(beast);
    }
}
