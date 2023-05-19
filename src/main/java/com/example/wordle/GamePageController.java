package com.example.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {

    private static int N;
    @FXML
    private VBox vbox;

    @FXML
    public void HandleButtonCLick(ActionEvent event) {
        if(event.getSource() instanceof Button){
            String btnValue = ((Button) event.getSource()).getText();
            System.out.println(btnValue);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Get numberOfLetters in order to setup the game environment
        GameMode game = GameMode.getInstance();
        N = game.getNumberOfLetters();

        // Setup game environment
        for(int i = 0; i < N + 1; i++){
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.setSpacing(5);
            for(int j = 0; j < N; j++) {
                Label label = new Label("");
                label.setFont(Font.font(34));
                label.setTextAlignment(TextAlignment.CENTER);
                label.setPrefWidth(40);
                label.setPrefHeight(40);
                label.setStyle("-fx-border-color: BLACK");
                hbox.getChildren().add(label); // Add label to hbox
            }
            vbox.getChildren().add(hbox); // Add hbox to vbox
        }
    }
}
