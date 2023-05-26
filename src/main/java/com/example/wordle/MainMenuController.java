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
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private Label welcome_text;

    @FXML
    public void HandleButtonClick(ActionEvent event) throws IOException {
        if(event.getSource() instanceof Button) {
            String btnValue = ((Button) event.getSource()).getText();
            FXMLLoader loader = null;

            switch (btnValue){
                case "New Game":
                    // switch scene to GameModePage
                    loader = new FXMLLoader(getClass().getResource("GameModePage.fxml"));
                    break;
                case "Leaderboard":
                    // switch scene to LeaderboardPage
                    loader = new FXMLLoader(getClass().getResource("LeaderboardPage.fxml"));
                    break;
                case "Settings":
                    // switch scene to SettingsPage
                    loader = new FXMLLoader(getClass().getResource("SettingsPage.fxml"));
                    break;
                case "Exit":
                    // exit program with status 0 -> success
                    System.exit(0);
                    break;
            }

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appstage.setScene(scene);
            appstage.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Language language = Language.getInstance();
        // by default
        language.setLang("en");
    }
}
