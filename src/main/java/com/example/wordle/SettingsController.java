package com.example.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    private Button back;
    @FXML
    private Label languageSelected;

    @FXML
    public void HandleButtonClick(ActionEvent event) throws IOException {
        if(event.getSource() instanceof Button) {
            String btnValue = ((Button) event.getSource()).getId();

            switch (btnValue){
                case "ukusaflag":
                    languageSelected.setText("Language selected: English");
                    language.setLang("en");
                    break;
                case "ptflag":
                    languageSelected.setText("Language selected: Português");
                    language.setLang("pt");
                    break;
                case "frflag":
                    languageSelected.setText("Language selected: Français");
                    language.setLang("fr");
                    break;
                case "back":
                    Parent mainPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
                    Scene mainPageScene = new Scene(mainPage);
                    Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appstage.setScene(mainPageScene);
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
