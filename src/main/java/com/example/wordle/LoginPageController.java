package com.example.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class LoginPageController {
    public static String IDvalue;

    @FXML TextField tfUsername;
    @FXML TextField tfPassword;
    @FXML Label lblErrorMessage;
    @FXML Button btnLogin;
    @FXML Hyperlink hlRegister;

    public void loginUser(ActionEvent event) throws Exception{
        DBconnection db = new DBconnection();
        Connection connection = db.getConnection();

        String userQuery = "SELECT COUNT(1) FROM users WHERE name = '" + tfUsername.getText() + "' AND password = '" + tfPassword.getText() + "'";

        if (!Objects.equals(tfUsername.getText(), "") && !Objects.equals(tfPassword.getText(), "")) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(userQuery);

                while (resultSet.next()) {
                    if (resultSet.getInt(1) == 1) {
                        String getID = "SELECT id FROM users WHERE name = '" + tfUsername.getText() +"'";

                        try {
                            Statement IDstatement = connection.createStatement();
                            ResultSet IDresultSet = IDstatement.executeQuery(getID);

                            if (IDresultSet.next()) {
                                IDvalue = IDresultSet.getString(1);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Parent mainPage = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                        Scene mainPageScene = new Scene(mainPage);
                        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        appstage.setScene(mainPageScene);
                    } else {
                        lblErrorMessage.setVisible(true);
                        lblErrorMessage.setText("Credenciais Incorretas!");

                        tfUsername.setText("");
                        tfPassword.setText("");
                        tfUsername.requestFocus();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            tfUsername.requestFocus();

            lblErrorMessage.setVisible(true);
            lblErrorMessage.setText("Preencha todos os campos!");
        }
    }

    public void goToRegister(ActionEvent event) throws IOException {
        Parent mainPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterPage.fxml")));
        Scene mainPageScene = new Scene(mainPage);
        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appstage.setScene(mainPageScene);
    }
}