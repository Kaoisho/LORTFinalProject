package com.kaoishoworks.lotrfinalproject.Models;

/**
 * Esta clase define los parametros que tiene un héroe de raza hobbit.
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */
public final class Hobbit extends Heroes {

    public Hobbit (String name, int hp, int armor) {
        super(name, hp, armor);
        setCurrentHp(getDEFAULT_HP());
        setRace("Hobbit");
    }
    /**
     * Método que llamará a las habilidades de los heroes aplicando bonificadores y penalizadores si procede.
     * @param beast bestia que le aplica el modificador al héroe
     */
    @Override
    public void applyModifiers(Beasts beast) {
        reduceArmor(beast);
        fearOfGoblins(beast);
    }
    /**
     * Método que disminuye el ataque del hobbit en 10 puntos si la bestia con la que se enfrenta es un goblin.
     * @param beast bestia que comprobará el hobbit para saber si ejecuta la habilidad.
     */
    private void fearOfGoblins(Beasts beast) {
        if(beast instanceof Goblin) {
            setAttack(getAttack()-10);
        }
    }
}
