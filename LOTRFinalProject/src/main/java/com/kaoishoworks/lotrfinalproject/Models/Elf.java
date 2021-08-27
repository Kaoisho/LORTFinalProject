package com.kaoishoworks.lotrfinalproject.Models;
/**
 * Esta clase define los parametros que tiene un héroe de raza elfo.
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */

public final class Elf extends Heroes {

    public Elf (String name, int hp, int armor) {
        super(name, hp, armor);
        setCurrentHp(getDEFAULT_HP());
        setRace("Elfo");
    }
    /**
     * Método que llamará a las habilidades de los heroes aplicando bonificadores y penalizadores si procede.
     * @param beast bestia que le aplica el modificador al héroe
     */
    @Override
    public void applyModifiers(Beasts beast) {
        reduceArmor(beast);
        hateToOrcs(beast);
    }
    /**
     * Método que aumenta el ataque del elfo en 10 puntos si la bestia con la que se enfrenta es un orco.
     * @param beast bestia que comprobará el elfo para saber si ejecuta la habilidad.
     */
    private void hateToOrcs(Beasts beast) {
        if (beast instanceof Orc) {
            setAttack(getAttack() + 10);
        }
    }
}
