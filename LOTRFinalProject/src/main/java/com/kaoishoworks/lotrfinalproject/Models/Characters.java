package com.kaoishoworks.lotrfinalproject.Models;
import javafx.scene.image.Image;
import lombok.*;

import java.util.Random;
/**
 * Esta clase define los parametros que tiene un personaje así como funciones que tiene cada personaje.
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */

@Data
@AllArgsConstructor
public abstract class Characters {
    private String name;
    private int currentHp;
    private final int DEFAULT_HP;
    private int armor;
    private int dices;
    private int attack;
    private int attackMod;
    @Setter(AccessLevel.PROTECTED)
    private String race;
    private Image characterImage;


    protected Characters(String name, int DEFAULT_HP, int armor, int dices, int attack, int attackMod, String race) {
        this.name = name;
        this.DEFAULT_HP = DEFAULT_HP;
        currentHp = DEFAULT_HP;
        this.armor = armor;
        this.dices = dices;
        this.attack = attack;
        this.attackMod = attackMod;
        this.race = race;
    }
    /**
     * Método que calcula el ataque del personaje, cogiendo el mayor valor de todos los dados que lance.
     * @return Valor de ataque.
     */
    public int attack() {
        Random random = new Random();
        int maxValue = 0, currentValue;
        for(int i = 0; i < getDices(); i++) {
            currentValue = random.nextInt((1)+attack+attackMod);
            if (currentValue > maxValue)
                maxValue = currentValue;
        }
        return maxValue;
    }
    /**
     * Método que comprueba si un personaje está muerto.
     * @return true si su vida es inferior o igual a 0.
     */
    public boolean isDead() {
        return getCurrentHp() <= 0;
    }
    /**
     * Método que aplica el daño al personaje, restando el ataque menos la armadura. El daño restante se resta a la vida.
     * @param enemyAttack Valor de ataque del enemigo.
     */
    public void applyDamage(int enemyAttack) {
        if(getArmor() <= enemyAttack) {
            setCurrentHp(getCurrentHp()-(enemyAttack-getArmor()));
        }

    }
    /**
     * Método que reestablece la vitalidad inicial del personaje.
     */
    public void restartHP() {
        currentHp = DEFAULT_HP;
    }

    @Override
    public String toString() {
        return "[" + race + "] " + name + " (Vida = " + currentHp + ", Armadura = " + armor + ")";
    }
}
