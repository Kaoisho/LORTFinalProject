package com.kaoishoworks.lotrfinalproject.Models;
/**
 * Esta clase define los parametros que tiene una bestia
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */
public abstract class Beasts extends Characters {
    protected Beasts(String name, int DEFAULT_HP, int armor) {
        super(name,DEFAULT_HP,armor,1,90,0,"");
    }

}
