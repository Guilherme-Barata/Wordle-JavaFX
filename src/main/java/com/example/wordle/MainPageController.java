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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    @FXML Button btnBack;
    @FXML Button btnGame;
    @FXML Label lblId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String id = LoginPageController.IDvalue;

        try {
            DBconnection db = new DBconnection();
            Connection connection = db.getConnection();

            String getName = "SELECT name FROM users WHERE id = " + id;

            try {
                Statement Namestatement = connection.createStatement();
                ResultSet NameresultSet = Namestatement.executeQuery(getName);

                if (NameresultSet.next()) {
                    String name = NameresultSet.getString(1);
                    lblId.setText(name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToPrivious(ActionEvent event) throws IOException {
        Parent LoginPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        Scene LoginPageScene = new Scene(LoginPage);
        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appstage.setScene(LoginPageScene);
    }

    @FXML
    public void goToGame(ActionEvent event) throws IOException {
        Parent LoginPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameModePage.fxml")));
        Scene LoginPageScene = new Scene(LoginPage);
        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appstage.setScene(LoginPageScene);
    }
}
