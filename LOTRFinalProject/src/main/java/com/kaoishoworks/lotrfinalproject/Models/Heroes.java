package com.kaoishoworks.lotrfinalproject.Models;

import lombok.Getter;
/**
 * Esta clase define los parametros que tiene un héroe así como métodos que cambian sus parametros según condiciones.
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */
public abstract class Heroes extends Characters {
    @Getter
    private final int DEFAULT_ARMOR;
    private final int DEFAULT_ATTACK;
    protected Heroes(String name, int hp, int armor) {
        super(name, hp, armor, 2, 100, 0, "");
        DEFAULT_ARMOR = armor;
        DEFAULT_ATTACK = getAttack();
    }
    /**
     * Método que llamará a las habilidades de los heroes aplicando bonificadores y penalizadores si procede.
     * @param beast bestia que le aplica el modificador al héroe
     */
    public abstract void applyModifiers(Beasts beast);
    /**
     * Método que hará que se reinicie los modificadores al final del turno.
     *
     */
    public void resetModifiers() {
        setArmor(DEFAULT_ARMOR);
        setAttack(DEFAULT_ATTACK);
    }
    /**
     * Método que hará que se ejecute la habilidad del orco de reducir armadura del heroe en el turno.
     * @param beast bestia que le aplica el modificador al héroe
     */
    protected void reduceArmor(Beasts beast) {
        if (beast instanceof Orc) {
            setArmor(getArmor() - (int)(getArmor() * 0.10));
        } else {
            setArmor(DEFAULT_ARMOR);
        }
    }
}
