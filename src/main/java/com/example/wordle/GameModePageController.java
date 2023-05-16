package com.example.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameModePageController {

    private int numberOfLetters;
    @FXML
    private Button goBack;
    @FXML
    private Button lang;

    @FXML
    public void HandleButtonCLick(ActionEvent event){
        if(event.getSource() instanceof Button){
            String btnValue = ((Button) event.getSource()).getText();
            System.out.println(btnValue);
            numberOfLetters = Character.getNumericValue(btnValue.charAt(0));
            System.out.println(numberOfLetters);
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
