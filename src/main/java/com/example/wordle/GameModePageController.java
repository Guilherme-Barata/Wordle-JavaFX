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

public class GameModePageController {

    private int numberOfLetters;

    @FXML
    private Button lang;

    @FXML
    public void HandleButtonCLick(ActionEvent event) throws IOException {
        if(event.getSource() instanceof Button){
            String btnValue = ((Button) event.getSource()).getText();
            System.out.println(btnValue);
            numberOfLetters = Character.getNumericValue(btnValue.charAt(0));
            System.out.println(numberOfLetters);

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
    public void HandleGobackClick() {
        System.out.println("Go back pressed");
    }

    @FXML
    public void HandleLanguageClick() {
        System.out.println("language pressed");
    }
}
