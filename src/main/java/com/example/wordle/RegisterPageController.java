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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class RegisterPageController {
    @FXML TextField tfUsername;
    @FXML TextField tfPassword;
    @FXML TextField tfConfirmPassword;
    @FXML Label lblErrorMessage;
    @FXML Button btnRegister;
    @FXML Hyperlink hlLogin;

    public void RegisterUser(ActionEvent event) throws Exception{
        DBconnection db = new DBconnection();
        Connection connection = db.getConnection();

        if (Objects.equals(tfUsername.getText(), "") || Objects.equals(tfPassword.getText(), "") || Objects.equals(tfConfirmPassword.getText(), "")){
            tfUsername.requestFocus();

            lblErrorMessage.setVisible(true);
            lblErrorMessage.setText("Fill all Fields!");
        } else if (!Objects.equals(tfPassword.getText(), tfConfirmPassword.getText())){
            tfPassword.requestFocus();
            tfPassword.setText("");
            tfConfirmPassword.setText("");

            lblErrorMessage.setVisible(true);
            lblErrorMessage.setText("Passwords Don't Match!");
        } else {
            String userQuery = "SELECT COUNT(1) FROM users WHERE name = '" + tfUsername.getText() + "'";

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(userQuery);

                while (resultSet.next()){
                    if (resultSet.getInt(1) == 1){
                        lblErrorMessage.setVisible(true);
                        lblErrorMessage.setText("Username Already Exists!");

                        tfUsername.setText("");
                        tfUsername.requestFocus();
                    } else {
                        String registerQuery = "INSERT INTO users (name, password, gamesplayed, gameswon) VALUES ('" + tfUsername.getText() + "', '" + tfPassword.getText() + "', 0, 0)";

                        try {
                            Statement statementR = connection.createStatement();
                            statementR.executeUpdate(registerQuery);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Parent mainPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
                        Scene mainPageScene = new Scene(mainPage);
                        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        appstage.setScene(mainPageScene);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void goToLogin(ActionEvent event) throws IOException {
        Parent LoginPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        Scene LoginPageScene = new Scene(LoginPage);
        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appstage.setScene(LoginPageScene);
    }
}
