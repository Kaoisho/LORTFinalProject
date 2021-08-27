package com.kaoishoworks.lotrfinalproject.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Esta clase ejecuta el video introductorio antes de pasar al menú principal.
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */

public class IntroController implements Initializable {

    @FXML
    private MediaView intro;
    private static final String INTRO_URL = "/videos/intro.mp4";

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(Objects.requireNonNull(this.getClass().getResource(INTRO_URL)).toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        intro.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnEndOfMedia(() -> {
            Stage window = (Stage)intro.getScene().getWindow();
            try {
                window.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menu.fxml")))));
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
