package com.kaoishoworks.lotrfinalproject.Models;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;
/**
 * Esta clase define dos listas predeterminadas. Una de héroes y otra de Bestias. Para su uso en Batalla predeterminada.
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */
public final class MainCharacters {
    public final List<Heroes> MAIN_HEROES = new ArrayList<>();
    public final List<Beasts> MAIN_BEASTS = new ArrayList<>();

    public MainCharacters() {
        MAIN_HEROES.add(new Elf("Legolas", 150, 30));
        MAIN_HEROES.add(new Human("Aragorn", 150, 50));
        MAIN_HEROES.add(new Human("Boromir", 100, 60));
        MAIN_HEROES.add(new Human("Gandalf", 300, 30));
        MAIN_HEROES.add(new Hobbit("Frodo", 20, 10));
        MAIN_BEASTS.add(new Orc("Lurtz", 200, 60));
        MAIN_BEASTS.add(new Orc("Shagrat", 220, 50));
        MAIN_BEASTS.add(new Goblin("Uglúk", 120, 30));
        MAIN_BEASTS.add(new Goblin("Mauhúr", 100, 30));
        MAIN_HEROES.get(0).setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/legolas.PNG"))));
        MAIN_HEROES.get(1).setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/aragorn.PNG"))));
        MAIN_HEROES.get(2).setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/boromir.PNG"))));
        MAIN_HEROES.get(3).setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/gandalf.PNG"))));
        MAIN_HEROES.get(4).setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/frodo.PNG"))));
        MAIN_BEASTS.get(0).setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/lurtz.PNG"))));
        MAIN_BEASTS.get(1).setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/orc.PNG"))));
        MAIN_BEASTS.get(2).setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/ugluk.PNG"))));
        MAIN_BEASTS.get(3).setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/goblin.PNG"))));
    }
}
