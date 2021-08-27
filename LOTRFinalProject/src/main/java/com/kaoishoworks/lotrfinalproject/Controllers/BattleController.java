package com.kaoishoworks.lotrfinalproject.Controllers;

import com.kaoishoworks.lotrfinalproject.Models.*;
import com.kaoishoworks.lotrfinalproject.util.CardMovement;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Esta clase define todos los elementos que hay en batalla así como funcionalidades dentro de la misma.
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */

public class BattleController {
    /*ToDo
     * Actualizar todos los gráficos.
     * Rehacer video del logo.
     */
    //Todos los elementos que hay en pantalla
    @FXML
    private TextArea battlelog;

    @FXML
    private Button nextTurnButton;

    @FXML
    private AnchorPane heroCard1;

    @FXML
    private ImageView charImageHC1;

    @FXML
    private ImageView imageHC1;

    @FXML
    private Label hpHC1;

    @FXML
    private Label raceHC1;

    @FXML
    private Label nameHC1;

    @FXML
    private Label armorHC1;

    @FXML
    private Label attackHC1;

    @FXML
    private AnchorPane heroCard2;

    @FXML
    private ImageView charImageHC2;

    @FXML
    private ImageView imageHC2;

    @FXML
    private Label hpHC2;

    @FXML
    private Label raceHC2;

    @FXML
    private Label nameHC2;

    @FXML
    private Label armorHC2;

    @FXML
    private Label attackHC2;

    @FXML
    private AnchorPane heroCard3;

    @FXML
    private ImageView charImageHC3;

    @FXML
    private ImageView imageHC3;

    @FXML
    private Label hpHC3;

    @FXML
    private Label raceHC3;

    @FXML
    private Label nameHC3;

    @FXML
    private Label armorHC3;

    @FXML
    private Label attackHC3;

    @FXML
    private AnchorPane heroCard4;

    @FXML
    private ImageView charImageHC4;

    @FXML
    private ImageView imageHC4;

    @FXML
    private Label hpHC4;

    @FXML
    private Label raceHC4;

    @FXML
    private Label nameHC4;

    @FXML
    private Label armorHC4;

    @FXML
    private Label attackHC4;

    @FXML
    private Button restartButton;

    @FXML
    private AnchorPane beastCard1;

    @FXML
    private ImageView charImageBC1;

    @FXML
    private ImageView imageBC1;

    @FXML
    private Label hpBC1;

    @FXML
    private Label raceBC1;

    @FXML
    private Label nameBC1;

    @FXML
    private Label armorBC1;

    @FXML
    private Label attackBC1;

    @FXML
    private AnchorPane beastCard2;

    @FXML
    private ImageView charImageBC2;

    @FXML
    private ImageView imageBC2;

    @FXML
    private Label hpBC2;

    @FXML
    private Label raceBC2;

    @FXML
    private Label nameBC2;

    @FXML
    private Label armorBC2;

    @FXML
    private Label attackBC2;

    @FXML
    private AnchorPane beastCard3;

    @FXML
    private ImageView charImageBC3;

    @FXML
    private ImageView imageBC3;

    @FXML
    private Label hpBC3;

    @FXML
    private Label raceBC3;

    @FXML
    private Label nameBC3;

    @FXML
    private Label armorBC3;

    @FXML
    private Label attackBC3;

    @FXML
    private AnchorPane beastCard4;

    @FXML
    private ImageView charImageBC4;

    @FXML
    private ImageView imageBC4;

    @FXML
    private Label hpBC4;

    @FXML
    private Label raceBC4;

    @FXML
    private Label nameBC4;

    @FXML
    private Label armorBC4;

    @FXML
    private Label attackBC4;

    @FXML
    private Button toMainMenu;

    @FXML
    private Label heroesWin;

    @FXML
    private Label heroesLose;

    @Getter
    @Setter
    private List<Heroes> heroes;

    @Getter
    @Setter
    private List<Beasts> beasts;
    private List<Heroes> iniHeroes;
    private List<Beasts> iniBeast;

    private int turn;
    private StringBuilder log;
    private Media swordSound, battle;
    private MediaPlayer mediaPlayer;

    public BattleController(List<Heroes> heroes, List<Beasts> beasts) {
        this.heroes = new ArrayList<>(heroes);
        this.beasts = new ArrayList<>(beasts);
        iniBeast = new ArrayList<>(beasts);
        iniHeroes = new ArrayList<>(heroes);
    }
    @FXML
    public void initialize() {
        turn = 0;
        log = new StringBuilder();
        nextTurnButton.setText("Empezar");

        //Usa .appendText("") para que haga scroll automatico.
        battlelog.textProperty().addListener(
                (ChangeListener<Object>) (observable, oldValue, newValue) -> battlelog.setScrollTop(Double.MAX_VALUE));
        updateCards();
        restartButton.setDisable(true);
        swordSound = new Media(Objects.requireNonNull(this.getClass().getResource("/sounds/steelsword.mp3")).toExternalForm());
        battle = new Media(Objects.requireNonNull(this.getClass().getResource("/sounds/battle.mp3")).toExternalForm());
        mediaPlayer = new MediaPlayer(battle);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setAutoPlay(true);
    }
    /**
     * Método que ejecuta un turno de la batalla.
     */
    @FXML
    @SneakyThrows
    void nextTurn(ActionEvent event) {
        int battles;
        disableButtonOnAnimation();
        playSwordSound();
        resetAttackValue();
        turn++;
        log.append("\nTurno ").append(turn).append(":");
        //Se aplica ataque y daños
        for(int i = 0; i < battlesInATurn(heroes,beasts); i++) {
            Heroes hero = heroes.get(i);
            Beasts beast = beasts.get(i);
            log.append("\nLucha entre:\n").append(hero).append(" \ny\n ").append(beast);
            hero.applyModifiers(beast);
            int heroAttack = hero.attack();
            int beastAttack = beast.attack();
            log.append("\n    ").append(hero.getName()).append(" saca ").append(heroAttack).append(" y le quita ")
                    .append(damage(beast.getArmor(), heroAttack)).append(" a ").append(beast.getName());
            beast.applyDamage(heroAttack);
            log.append("\n    ").append(beast.getName()).append(" saca ").append(beastAttack).append(" y le quita ")
                    .append(damage(hero.getArmor(), beastAttack)).append(" a ").append(hero.getName());
            hero.applyDamage(beastAttack);
            updateAttackCard(i,heroAttack,beastAttack);
            moveCards(i);
            //Se reinician modificadores
            hero.resetModifiers();
            if(hero.isDead()) {
                log.append("\n  ¡Muere ").append(hero.getRace()).append(" ").append(hero.getName()).append("!");
            }
            if (beast.isDead()) {
                log.append("\n  ¡Muere ").append(beast.getRace()).append(" ").append(beast.getName()).append("!");
            }
            removeTheFallen(heroes, beasts);

        }
        if(heroes.size() == 0) {
            mediaPlayer.stop();
            log.append("\n¡LAS BESTIAS HAN GANADO!");
            heroesLose.setOpacity(1.0);
            mediaPlayer = new MediaPlayer(
                    new Media(Objects.requireNonNull(this.getClass()
                            .getResource("/sounds/heroes_lose.mp3")).toExternalForm()));
            mediaPlayer.play();

        }
        if (beasts.size() == 0) {
            mediaPlayer.stop();
            log.append("\n¡LOS HEROES HAN GANADO!");
            heroesWin.setOpacity(1.0);
            mediaPlayer = new MediaPlayer(
                    new Media(Objects.requireNonNull(this.getClass()
                            .getResource("/sounds/heroes_win.mp3")).toExternalForm()));
            mediaPlayer.play();

        }
        battlelog.setText(log.toString());
        battlelog.appendText("");
        nextTurnButton.setText("Siguiente Turno");
        updateCards();
    }

    /**
     * Método que reinicia el juego con los personajes seleccionados.
     */
    @FXML
    void restartGame(ActionEvent event) {
        mediaPlayer.stop();
        iniHeroes.forEach(Characters::restartHP);
        iniBeast.forEach(Characters::restartHP);
        BattleController mbc = new BattleController(iniHeroes,iniBeast);
        Stage window = (Stage)restartButton.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/battle.fxml"));
            loader.setController(mbc);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que te dirige al menú principal.
     */
    @FXML
    void goToMainMenu(ActionEvent event) {
        mediaPlayer.stop();
        Stage window = (Stage)toMainMenu.getScene().getWindow();
        try {
            window.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menu.fxml")))));
            window.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Método que actualiza las cartas. Si el personaje ha muerto, se elimina del campo de batalla.
     * y se mueven de posición de derecha a izquierda.
     */
    private void updateCards() {
        for (int i = 0; i < heroes.size(); i++) {
            getHeroCardName(i).setText(heroes.get(i).getName());
            getHeroCardRace(i).setText(heroes.get(i).getRace());
            getHeroCardArmor(i).setText("" + heroes.get(i).getArmor());
            getHeroCardHp(i).setText("" + heroes.get(i).getCurrentHp());
            getHeroImage(i).setImage(heroes.get(i).getCharacterImage());
        }
        if (heroes.size() == 3) {
            getHeroCardName(3).setText("");
            getHeroCardRace(3).setText("");
            getHeroCardArmor(3).setText("");
            getHeroCardHp(3).setText("");
            getHeroCardAttack(3).setText("");
            charImageHC4.setOpacity(0);
            imageHC4.setOpacity(0);

        }
        if (heroes.size() == 2) {
            getHeroCardName(3).setText("");
            getHeroCardRace(3).setText("");
            getHeroCardArmor(3).setText("");
            getHeroCardHp(3).setText("");
            getHeroCardAttack(3).setText("");
            getHeroCardName(2).setText("");
            getHeroCardRace(2).setText("");
            getHeroCardArmor(2).setText("");
            getHeroCardHp(2).setText("");
            getHeroCardAttack(2).setText("");
            imageHC4.setOpacity(0);
            imageHC3.setOpacity(0);
            charImageHC4.setOpacity(0);
            charImageHC3.setOpacity(0);
        }
        if (heroes.size() == 1) {
            getHeroCardName(3).setText("");
            getHeroCardRace(3).setText("");
            getHeroCardArmor(3).setText("");
            getHeroCardHp(3).setText("");
            getHeroCardAttack(3).setText("");
            getHeroCardName(2).setText("");
            getHeroCardRace(2).setText("");
            getHeroCardArmor(2).setText("");
            getHeroCardHp(2).setText("");
            getHeroCardAttack(2).setText("");
            getHeroCardName(1).setText("");
            getHeroCardRace(1).setText("");
            getHeroCardArmor(1).setText("");
            getHeroCardHp(1).setText("");
            getHeroCardAttack(1).setText("");
            imageHC4.setOpacity(0);
            imageHC3.setOpacity(0);
            imageHC2.setOpacity(0);
            charImageHC4.setOpacity(0);
            charImageHC3.setOpacity(0);
            charImageHC2.setOpacity(0);

        }
        if (heroes.size() == 0) {
            getHeroCardName(3).setText("");
            getHeroCardRace(3).setText("");
            getHeroCardArmor(3).setText("");
            getHeroCardHp(3).setText("");
            getHeroCardAttack(3).setText("");
            getHeroCardName(2).setText("");
            getHeroCardRace(2).setText("");
            getHeroCardArmor(2).setText("");
            getHeroCardHp(2).setText("");
            getHeroCardAttack(2).setText("");
            getHeroCardName(1).setText("");
            getHeroCardRace(1).setText("");
            getHeroCardArmor(1).setText("");
            getHeroCardHp(1).setText("");
            getHeroCardAttack(1).setText("");
            getHeroCardName(0).setText("");
            getHeroCardRace(0).setText("");
            getHeroCardArmor(0).setText("");
            getHeroCardHp(0).setText("");
            getHeroCardAttack(0).setText("");
            imageHC4.setOpacity(0);
            imageHC3.setOpacity(0);
            imageHC2.setOpacity(0);
            imageHC1.setOpacity(0);
            charImageHC4.setOpacity(0);
            charImageHC3.setOpacity(0);
            charImageHC2.setOpacity(0);
            charImageHC1.setOpacity(0);
        }
        for (int i = 0; i < beasts.size(); i++) {
            getBeastCardName(i).setText(beasts.get(i).getName());
            getBeastCardRace(i).setText(beasts.get(i).getRace());
            getBeastCardArmor(i).setText("" + beasts.get(i).getArmor());
            getBeastCardHp(i).setText("" + beasts.get(i).getCurrentHp());
            getBeastImage(i).setImage(beasts.get(i).getCharacterImage());
        }
        if (beasts.size() == 3) {
            getBeastCardName(3).setText("");
            getBeastCardRace(3).setText("");
            getBeastCardArmor(3).setText("");
            getBeastCardHp(3).setText("");
            getBeastCardAttack(3).setText("");
            imageBC4.setOpacity(0);
            charImageBC4.setOpacity(0);
        }
        if (beasts.size() == 2) {
            getBeastCardName(3).setText("");
            getBeastCardRace(3).setText("");
            getBeastCardArmor(3).setText("");
            getBeastCardHp(3).setText("");
            getBeastCardAttack(3).setText("");
            getBeastCardName(2).setText("");
            getBeastCardRace(2).setText("");
            getBeastCardArmor(2).setText("");
            getBeastCardHp(2).setText("");
            getBeastCardAttack(2).setText("");
            imageBC4.setOpacity(0);
            imageBC3.setOpacity(0);
            charImageBC4.setOpacity(0);
            charImageBC3.setOpacity(0);
        }
        if (beasts.size() == 1) {
            getBeastCardName(3).setText("");
            getBeastCardRace(3).setText("");
            getBeastCardArmor(3).setText("");
            getBeastCardHp(3).setText("");
            getBeastCardAttack(3).setText("");
            getBeastCardName(2).setText("");
            getBeastCardRace(2).setText("");
            getBeastCardArmor(2).setText("");
            getBeastCardHp(2).setText("");
            getBeastCardAttack(2).setText("");
            getBeastCardName(1).setText("");
            getBeastCardRace(1).setText("");
            getBeastCardArmor(1).setText("");
            getBeastCardHp(1).setText("");
            getBeastCardAttack(1).setText("");
            imageBC4.setOpacity(0);
            imageBC3.setOpacity(0);
            imageBC2.setOpacity(0);
            charImageBC4.setOpacity(0);
            charImageBC3.setOpacity(0);
            charImageBC2.setOpacity(0);
        }
        if (beasts.size() == 0) {
            getBeastCardName(3).setText("");
            getBeastCardRace(3).setText("");
            getBeastCardArmor(3).setText("");
            getBeastCardHp(3).setText("");
            getBeastCardAttack(3).setText("");
            getBeastCardName(2).setText("");
            getBeastCardRace(2).setText("");
            getBeastCardArmor(2).setText("");
            getBeastCardHp(2).setText("");
            getBeastCardAttack(2).setText("");
            getBeastCardName(1).setText("");
            getBeastCardRace(1).setText("");
            getBeastCardArmor(1).setText("");
            getBeastCardHp(1).setText("");
            getBeastCardAttack(1).setText("");
            getBeastCardName(0).setText("");
            getBeastCardRace(0).setText("");
            getBeastCardArmor(0).setText("");
            getBeastCardHp(0).setText("");
            getBeastCardAttack(0).setText("");
            imageBC4.setOpacity(0);
            imageBC3.setOpacity(0);
            imageBC2.setOpacity(0);
            imageBC1.setOpacity(0);
            charImageBC4.setOpacity(0);
            charImageBC3.setOpacity(0);
            charImageBC2.setOpacity(0);
            charImageBC1.setOpacity(0);
        }
    }

    /**
     * Método que devuelve el Label del nombre de un héroe según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Label del nombre del héroe.
     */
    private Label getHeroCardName(int position) {
        switch (position) {
            case 0:
                return nameHC1;
            case 1:
                return nameHC2;
            case 2:
                return nameHC3;
            case 3:
                return nameHC4;
        }
        return new Label("");
    }

    /**
     * Método que devuelve el Label de la raza de un héroe según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Label con la raza del héroe.
     */
    private Label getHeroCardRace(int position) {
        switch (position) {
            case 0:
                return raceHC1;
            case 1:
                return raceHC2;
            case 2:
                return raceHC3;
            case 3:
                return raceHC4;
        }
        return new Label("");
    }

    /**
     * Método que devuelve el Label de la armadura de un héroe según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Label con la armadura del héroe.
     */
    private Label getHeroCardArmor(int position) {
        switch (position) {
            case 0:
                return armorHC1;
            case 1:
                return armorHC2;
            case 2:
                return armorHC3;
            case 3:
                return armorHC4;
        }
        return new Label("");
    }
    /**
     * Método que devuelve el Label de la vida de un héroe según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Label con la vida del héroe.
     */
    private Label getHeroCardHp(int position) {
        switch (position) {
            case 0:
                return hpHC1;
            case 1:
                return hpHC2;
            case 2:
                return hpHC3;
            case 3:
                return hpHC4;
        }
        return new Label("");
    }
    /**
     * Método que devuelve el Label con el ataque de un héroe según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Label con el ataque del héroe.
     */
    private Label getHeroCardAttack(int position) {
        switch (position) {
            case 0:
                return attackHC1;
            case 1:
                return attackHC2;
            case 2:
                return attackHC3;
            case 3:
                return attackHC4;
        }
        return new Label("");
    }
    /**
     * Método que devuelve el Label del nombre de una bestia según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Label del nombre de la bestia.
     */
    private Label getBeastCardName(int position) {
        switch (position) {
            case 0:
                return nameBC1;
            case 1:
                return nameBC2;
            case 2:
                return nameBC3;
            case 3:
                return nameBC4;
        }
        return new Label("");
    }
    /**
     * Método que devuelve el Label de la raza de una bestia según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Label de la raza de la bestia.
     */
    private Label getBeastCardRace(int position) {
        switch (position) {
            case 0:
                return raceBC1;
            case 1:
                return raceBC2;
            case 2:
                return raceBC3;
            case 3:
                return raceBC4;
        }
        return new Label("");
    }
    /**
     * Método que devuelve el Label de la armadura de una bestia según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Label de la armadura de la bestia.
     */
    private Label getBeastCardArmor(int position) {
        switch (position) {
            case 0:
                return armorBC1;
            case 1:
                return armorBC2;
            case 2:
                return armorBC3;
            case 3:
                return armorBC4;
        }
        return new Label("");
    }
    /**
     * Método que devuelve el Label de la vida de una bestia según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Label de la vida de la bestia.
     */
    private Label getBeastCardHp(int position) {
        switch (position) {
            case 0:
                return hpBC1;
            case 1:
                return hpBC2;
            case 2:
                return hpBC3;
            case 3:
                return hpBC4;
        }
        return new Label("");
    }
    /**
     * Método que devuelve el Label del ataque de una bestia según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Label del ataque de la bestia.
     */
    private Label getBeastCardAttack(int position) {
        switch (position) {
            case 0:
                return attackBC1;
            case 1:
                return attackBC2;
            case 2:
                return attackBC3;
            case 3:
                return attackBC4;
        }
        return new Label("");
    }
    /**
     * Método que actualiza las vistas de los ataques de los personajes.
     * @param position Posición en el campo de batalla.
     * @param heroAttack Ataque del héroe.
     * @param beastAttack Ataque de la bestia.
     */
    private void updateAttackCard(int position, int heroAttack, int beastAttack) {
        switch (position) {
            case 0:
                attackHC1.setText("" + heroAttack);
                attackBC1.setText("" + beastAttack);
                break;
            case 1:
                attackHC2.setText("" + heroAttack);
                attackBC2.setText("" + beastAttack);
                break;
            case 2:
                attackHC3.setText("" + heroAttack);
                attackBC3.setText("" + beastAttack);
                break;
            case 3:
                attackHC4.setText("" + heroAttack);
                attackBC4.setText("" + beastAttack);
                break;
            default:
                break;
        }
    }
    /**
     * Método que pone en blanco los Label que indica el ataque del personaje.
     */
    private void resetAttackValue() {
        attackHC1.setText("");
        attackHC2.setText("");
        attackHC3.setText("");
        attackHC4.setText("");
        attackBC1.setText("");
        attackBC2.setText("");
        attackBC3.setText("");
        attackBC4.setText("");
    }
    /**
     * Método que habilita de nuevo las cartas que han sido eliminadas del campo de batalla.
     */
    private void restartCards() {
        imageHC1.setOpacity(1);
        imageHC2.setOpacity(1);
        imageHC3.setOpacity(1);
        imageHC4.setOpacity(1);
        imageBC1.setOpacity(1);
        imageBC2.setOpacity(1);
        imageBC3.setOpacity(1);
        imageBC4.setOpacity(1);
    }
    /**
     * Método que calcula cuantas batallas se realizaran en un turno.
     * @param heroes Lista de heroes
     * @param beasts Lista de bestias
     * @return Numero de batallas a realizar en el turno hasta un máximo de 4.
     */
    private int battlesInATurn(List<Heroes> heroes, List<Beasts> beasts) {
        if (heroes.size() >= 4 && beasts.size() >= 4)
            return 4;
        return Math.min(heroes.size(), beasts.size());
    }
    /**
     * Método que calcula el daño que se inflinge.
     * @param armor Valor de armadura.
     * @param attack Valor de ataque.
     * @return Valor de daño. Si la armadura es superior al ataque, devuelve 0.
     */
    private int damage(int armor, int attack) {
        if (armor >= attack) {
            return 0;
        }
        return attack-armor;
    }
    /**
     * Método que comprueba en las listas si un personaje ha muerto y lo elimina de la lista si lo está.
     * @param heroes Lista de heroes a comprobar.
     * @param beasts Lista de bestias a comprobar.
     */
    private void removeTheFallen(List<Heroes> heroes, List<Beasts> beasts) {
        heroes.removeIf(Characters::isDead);
        beasts.removeIf(Characters::isDead);
    }
    /**
     * Método que devuelve la imagen asociada al héroe según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Imagen asociada al héroe.
     */
    private ImageView getHeroImage(int position) {
        switch (position) {
            case 0:
                return charImageHC1;
            case 1:
                return charImageHC2;
            case 2:
                return charImageHC3;
            case 3:
                return charImageHC4;
        }
        return new ImageView();
    }
    /**
     * Método que devuelve la imagen asociada a la bestia según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Imagen asociada a la bestia.
     */
    private ImageView getBeastImage(int position) {
        switch (position) {
            case 0:
                return charImageBC1;
            case 1:
                return charImageBC2;
            case 2:
                return charImageBC3;
            case 3:
                return charImageBC4;
        }
        return new ImageView();
    }
    /**
     * Método que devuelve la carta de héroe según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Carta del héroe.
     */
    private AnchorPane getHeroCard(int position) {
        switch (position) {
            case 0:
                return heroCard1;
            case 1:
                return heroCard2;
            case 2:
                return heroCard3;
            case 3:
                return heroCard4;
        }
        return new AnchorPane();
    }
    /**
     * Método que devuelve la carta de bestia según posición en el campo de batalla.
     * @param position Posición en el campo de batalla.
     * @return Carta de bestia.
     */
    private AnchorPane getBeastCard(int position) {
        switch (position) {
            case 0:
                return beastCard1;
            case 1:
                return beastCard2;
            case 2:
                return beastCard3;
            case 3:
                return beastCard4;
        }
        return new AnchorPane();
    }
    /**
     * Método que ejecuta la animación de batalla de las cartas.
     * @param position Posición en el campo de batalla.
     */
    private void moveCards(int position) {
        CardMovement cardMovement = new CardMovement();
        Thread thread = new Thread(()-> {
            cardMovement.moveHeroCard(
                    getHeroCard(position).getScene(),
                    getHeroCard(position),
                    cardMovement.createTranslateTransition(getHeroCard(position)));
            cardMovement.moveBeastCard(
                    getBeastCard(position).getScene(),
                    getBeastCard(position),
                    cardMovement.createTranslateTransition(getBeastCard(position)));
        });
        thread.start();
    }
    /**
     * Método que deshabilita el botón "next Turn" durante un tiempo de 400ms.
     * Si se acaba la partida, se deshabilita por completo.
     */
    private void disableButtonOnAnimation() {
        Thread thread = new Thread(() -> {
            nextTurnButton.setDisable(true);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (heroes.size() == 0 || beasts.size() == 0) {
                nextTurnButton.setDisable(true);
                restartButton.setDisable(false);
            } else {
                nextTurnButton.setDisable(false);
            }
        });
        thread.start();
    }
    /**
     * Método que ejecuta el sonido de espadas.
     */
    private void playSwordSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(swordSound);
        mediaPlayer.play();
    }
}
