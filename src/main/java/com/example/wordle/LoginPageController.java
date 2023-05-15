package com.example.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import java.util.Objects;

public class LoginPageController {
    @FXML TextField tfUsername;
    @FXML TextField tfPassword;
    @FXML Button btnLogin;

    public void login(ActionEvent event) throws Exception{
        DBconnection db = new DBconnection();
        Connection connection = db.getConnection();

        String userQuery = "SELECT COUNT(1) FROM users WHERE name = '" + tfUsername.getText() + "' AND password = '" + tfPassword.getText() + "'";

        if (!Objects.equals(tfUsername.getText(), "") && !Objects.equals(tfPassword.getText(), "")){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(userQuery);

                while (resultSet.next()){
                    if (resultSet.getInt(1) == 1){
                        System.out.println("Logged in");
                        Parent mainPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainPage.fxml")));
                        Scene mainPageScene = new Scene(mainPage);
                        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        appstage.setScene(mainPageScene);
                    } else {
                        System.out.println("Credenciais erradas!");
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
            System.out.println("Preencha todos os campos!");
        }
    }
}