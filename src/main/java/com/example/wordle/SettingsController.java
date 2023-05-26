package com.example.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    private String lang;
    private Language language;
    @FXML
    private Button ukusaflag;
    @FXML
    private Button ptflag;
    @FXML
    private Button frflag;
    @FXML
    private Label languageSelected;

    @FXML
    public void HandleButtonClick(ActionEvent event) {
        if(event.getSource() instanceof Button) {
            String btnValue = ((Button) event.getSource()).getId();

            switch (btnValue){
                case "ukusaflag":
                    languageSelected.setText("Language selected: English");
                    language.setLang("en");
                    System.out.println(language.getLang());
                    break;
                case "ptflag":
                    languageSelected.setText("Language selected: Português");
                    language.setLang("pt");
                    System.out.println(language.getLang());
                    break;
                case "frflag":
                    languageSelected.setText("Language selected: Français");
                    language.setLang("fr");
                    System.out.println(language.getLang());
                    break;
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        language = Language.getInstance();
        lang = language.getLang();
        switch (lang){
            case "en":
                lang = "English";
                break;
            case "pt":
                lang = "Português";
                break;
            case "fr":
                lang = "Français";
                break;
        }
        languageSelected.setText(languageSelected.getText() + " " + lang);
    }
}
