package com.example.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameModePageController{

    private int numberOfLetters;

    @FXML
    public void HandleButtonCLick(ActionEvent event) throws IOException {
        if(event.getSource() instanceof Button){
            String btnValue = ((Button) event.getSource()).getText();
            numberOfLetters = Character.getNumericValue(btnValue.charAt(0));

            // set numberOfLetters to be used in the Game
            GameMode game = GameMode.getInstance();
            game.setNumberOfLetters(numberOfLetters);

            // Switch to GamePage
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GamePage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appstage.setScene(scene);
            appstage.show();
        }
    }

    @FXML
    public void goback (ActionEvent event) throws IOException {
        Parent mainPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        Scene mainPageScene = new Scene(mainPage);
        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appstage.setScene(mainPageScene);
    }

}
