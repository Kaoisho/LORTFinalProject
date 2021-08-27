package com.kaoishoworks.lotrfinalproject.Controllers;

import com.kaoishoworks.lotrfinalproject.Models.MainCharacters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Esta clase define todos los elementos que hay en el menú.
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */
public class MenuController {

    @FXML
    private Button mainBattleButton;

    @FXML
    private Button customBattleButton;

    private Media intro;
    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        intro = new Media(Objects.requireNonNull(this.getClass().getResource("/sounds/intro.mp3")).toExternalForm());
        mediaPlayer = new MediaPlayer(intro);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
    }

    /**
     * Método que lanza la batalla predefinida con los personajes predefinidos.
     */
    @FXML
    void launchMainBattle(ActionEvent event) {
        mediaPlayer.stop();
        Stage window = (Stage)mainBattleButton.getScene().getWindow();
        MainCharacters mc = new MainCharacters();
        BattleController mbc = new BattleController(mc.MAIN_HEROES,mc.MAIN_BEASTS);
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
     * Método que lanza un menú para crear las listas de héroes y bestias y realizar una batalla personalizada.
     */
    @FXML
    void launchCustomBattle(ActionEvent event) {
        mediaPlayer.stop();
        Stage window = (Stage)customBattleButton.getScene().getWindow();
        try {
            window.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/preCustomBattle.fxml")))));
            window.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


}