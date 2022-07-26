package com.example.carpre.carpre;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private Button outSingButton;

    @FXML
    private PasswordField passwordField;

    // Play Audio Effects Method!
    @FXML
    MediaPlayer mediaPlayer;
    void playAudioEffects(String file){
        Media h= new Media(Paths .get(file).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }
    // Initialize run main
    @FXML
    void initialize() {
        outSingButton.setOnAction(actionEvent -> {
            String login = loginField.getText();
            String password = passwordField.getText();

            if (login.equals("admin") && password.equals("1234")) {
                playAudioEffects("src/main/resources/audio/welcome.mp3");
                openNewWindow("main.fxml");

            }
            else {playAudioEffects("src/main/resources/audio/error.mp3");}
        });
    }

    // Open a new window Method...
    void openNewWindow(String window){
        outSingButton.getScene().getWindow().hide();

        FXMLLoader winLoader = new FXMLLoader();
        winLoader.setLocation(getClass().getResource(window));

        try {
            winLoader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Parent root = winLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
