package com.kaoishoworks.lotrfinalproject.Controllers;

import com.kaoishoworks.lotrfinalproject.Models.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Esta clase define todos los elementos que hay en el menú de batalla personalizada.
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */
public class preCustomBattleController {

    @FXML
    private ListView<Heroes> heroesList;

    @FXML
    private ListView<Beasts> beastsList;

    @FXML
    private TextField nameHero;

    @FXML
    private TextField hpHero;

    @FXML
    private TextField armorHero;

    @FXML
    private ChoiceBox<String> raceHero;

    @FXML
    private Button addHeroButton;

    @FXML
    private TextField nameBeast;

    @FXML
    private TextField hpBeast;

    @FXML
    private TextField armorBeast;

    @FXML
    private ChoiceBox<String> raceBeast;

    @FXML
    private Button addBeastButton;

    @FXML
    private Button upPosHeroButton;

    @FXML
    private Button downPosHeroButton;

    @FXML
    private Button clearHeroButton;

    @FXML
    private Button upPosBeastButton;

    @FXML
    private Button downPosBeastButton;

    @FXML
    private Button clearBeastButton;

    @FXML
    private Button startBattleButton;

    @FXML
    private Button toTheMenu;
    private Media intro;
    private MediaPlayer mediaPlayer;

    private List<Beasts> beasts = new ArrayList<>();
    private List<Heroes> heroes = new ArrayList<>();
    protected ListProperty<Beasts> beastsLP = new SimpleListProperty<>();
    protected ListProperty<Heroes> heroesLP = new SimpleListProperty<>();

    @FXML
    public void initialize() {
        intro = new Media(Objects.requireNonNull(this.getClass().getResource("/sounds/intro.mp3")).toExternalForm());
        mediaPlayer = new MediaPlayer(intro);
        mediaPlayer.setVolume(0.5);
        beastsList.itemsProperty().bind(beastsLP);
        heroesList.itemsProperty().bind(heroesLP);
        beastsLP.set(FXCollections.observableArrayList(beasts));
        heroesLP.set(FXCollections.observableArrayList(heroes));
        raceBeast.getItems().addAll("Orco", "Goblin");
        raceHero.getItems().addAll("Elfo", "Humano", "Hobbit");
        onlyNumbersTextField(hpBeast);
        onlyNumbersTextField(hpHero);
        onlyNumbersTextField(armorBeast);
        onlyNumbersTextField(armorHero);
        setButtonsOnDisable();
        mediaPlayer.play();
    }
    /**
     * Método que añade una bestia a la lista y hace visible el elemento en el ListView.
     */
    @FXML
    void addBeast(ActionEvent event) {
        switch (raceBeast.getValue()) {
            case "Orco":
                Orc orc = new Orc(nameBeast.getText(), Integer.parseInt(hpBeast.getText()),
                        Integer.parseInt(armorBeast.getText()));
                orc.setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/orc.PNG"))));
                beasts.add(orc);
                beastsLP.set(FXCollections.observableArrayList(beasts));
                break;
            case "Goblin":
                Goblin goblin = new Goblin(nameBeast.getText(), Integer.parseInt(hpBeast.getText()),
                        Integer.parseInt(armorBeast.getText()));
                goblin.setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/goblin.PNG"))));
                beasts.add(goblin);
                beastsLP.set(FXCollections.observableArrayList(beasts));
                break;
        }
    }
    /**
     * Método que añade un héroe a la lista y hace visible el elemento en el ListView.
     */
    @FXML
    void addHero(ActionEvent event) {
        switch (raceHero.getValue()) {
            case "Elfo":
                Elf elf = new Elf(nameHero.getText(), Integer.parseInt(hpHero.getText()),
                        Integer.parseInt(armorHero.getText()));
                elf.setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/elf.PNG"))));
                heroes.add(elf);
                heroesLP.set(FXCollections.observableArrayList(heroes));
                break;
            case "Humano":
                Human human = new Human(nameHero.getText(), Integer.parseInt(hpHero.getText()),
                        Integer.parseInt(armorHero.getText()));
                human.setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/human.PNG"))));
                heroes.add(human);
                heroesLP.set(FXCollections.observableArrayList(heroes));
                break;
            case "Hobbit":
                Hobbit hobbit = new Hobbit(nameHero.getText(), Integer.parseInt(hpHero.getText()),
                        Integer.parseInt(armorHero.getText()));
                hobbit.setCharacterImage(new Image(String.valueOf(getClass().getResource("/images/hobbit.PNG"))));
                heroes.add(hobbit);
                heroesLP.set(FXCollections.observableArrayList(heroes));
                break;
        }

    }
    /**
     * Método que elimina de la lista una bestia seleccionada en el ListView de bestias.
     */
    @FXML
    void clearBeast(ActionEvent event) {
       beasts.remove(beastsList.getSelectionModel().getSelectedItem());
       beastsLP.set(FXCollections.observableArrayList(beasts));
    }
    /**
     * Método que baja de posición de la lista una bestia seleccionada en el ListView de bestias.
     */
    @FXML
    void downPositionBeast(ActionEvent event) {
        int index = beastsList.getSelectionModel().getSelectedIndex();
        if (index < beasts.size()-1) {
            Beasts beast = beasts.get(index);
            Beasts beast2 = beasts.get(index+1);
            beasts.set(index + 1, beast);
            beasts.set(index, beast2);
            beastsLP.set(FXCollections.observableArrayList(beasts));
        }
    }
    /**
     * Método que baja de posición de la lista un héroe seleccionado en el ListView de héroes.
     */
    @FXML
    void downPositionHero(ActionEvent event) {
        int index = heroesList.getSelectionModel().getSelectedIndex();
        if (index < heroes.size()-1) {
            Heroes hero = heroes.get(index);
            Heroes hero2 = heroes.get(index+1);
            heroes.set(index + 1, hero);
            heroes.set(index, hero2);
            heroesLP.set(FXCollections.observableArrayList(heroes));
        }

    }
    /**
     * Método que elimina de la lista un héroe seleccionado en el ListView de héroes.
     */
    @FXML
    void clearHero(ActionEvent event) {
        heroes.remove(heroesList.getSelectionModel().getSelectedItem());
        heroesLP.set(FXCollections.observableArrayList(heroes));
    }
    /**
     * Método que coge las dos listas creadas y te dirije a la batalla con esas listas.
     */
    @FXML
    void toTheBattle(ActionEvent event) {
        mediaPlayer.stop();
        Stage window = (Stage)nameHero.getScene().getWindow();
        BattleController mbc = new BattleController(heroes,beasts);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/battle.fxml"));
            loader.setController(mbc);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Método que sube de posición de la lista una bestia seleccionada en el ListView de bestias.
     */
    @FXML
    void upPositionBeast(ActionEvent event) {
        int index = beastsList.getSelectionModel().getSelectedIndex();
        if (index > 0) {
            Beasts beast = beasts.get(index);
            Beasts beast2 = beasts.get(index-1);
            beasts.set(index - 1, beast);
            beasts.set(index, beast2);
            beastsLP.set(FXCollections.observableArrayList(beasts));
        }

    }
    /**
     * Método que sube de posición de la lista un héroe seleccionado en el ListView de héroes.
     */
    @FXML
    void upPositionHero(ActionEvent event) {
        int index = heroesList.getSelectionModel().getSelectedIndex();
        if (index > 0) {
            Heroes hero = heroes.get(index);
            Heroes hero2 = heroes.get(index-1);
            heroes.set(index - 1, hero);
            heroes.set(index, hero2);
            heroesLP.set(FXCollections.observableArrayList(heroes));
        }
    }
    /**
     * Método que te redirige al menú principal.
     */
    @FXML
    void goToTheMenu(ActionEvent event) {
        mediaPlayer.stop();
        Stage window = (Stage)toTheMenu.getScene().getWindow();
        try {
            window.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menu.fxml")))));
            window.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Método que impide a un textField insertarle cualquier cosa que no sea valores numéricos.
     * @param tf Campo de texto que quieres que se aplique el metodo.
     */
    private void onlyNumbersTextField(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    /**
     * Método que deshabilita los botones si no cumplen ciertas condiciones.
     */
    private void setButtonsOnDisable() {
        addBeastButton.disableProperty().bind(nameBeast.textProperty().isEmpty()
                .or(hpBeast.textProperty().isEmpty())
                .or(armorBeast.textProperty().isEmpty())
                .or(Bindings.isNull(raceBeast.valueProperty())));
        addHeroButton.disableProperty().bind(nameHero.textProperty().isEmpty()
                .or(hpHero.textProperty().isEmpty())
                .or(armorHero.textProperty().isEmpty())
                .or(Bindings.isNull(raceHero.valueProperty())));
        upPosBeastButton.disableProperty().bind(beastsList.getSelectionModel().selectedItemProperty().isNull());
        downPosBeastButton.disableProperty().bind(beastsList.getSelectionModel().selectedItemProperty().isNull());
        clearBeastButton.disableProperty().bind(beastsList.getSelectionModel().selectedItemProperty().isNull());
        upPosHeroButton.disableProperty().bind(heroesList.getSelectionModel().selectedItemProperty().isNull());
        downPosHeroButton.disableProperty().bind(heroesList.getSelectionModel().selectedItemProperty().isNull());
        clearHeroButton.disableProperty().bind(heroesList.getSelectionModel().selectedItemProperty().isNull());
        startBattleButton.disableProperty().bind(heroesLP.emptyProperty()
                .or(beastsLP.emptyProperty()));
    }
}
