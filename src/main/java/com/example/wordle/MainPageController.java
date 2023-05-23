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
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Menu Principal");

        // Carregar o arquivo FXML do menu principal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 300);

        // Obter o controlador da página principal
        MainPageController controller = loader.getController();

        // Botão "Jogar"
        Button jogarButton = (Button) root.lookup("#jogarButton");
        jogarButton.setOnAction(e -> {
            try {
                // Abrir a página de login
                FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
                Parent loginRoot = loginLoader.load();
                Scene loginScene = new Scene(loginRoot, 400, 300);
                primaryStage.setScene(loginScene);

                // Obter o controlador da página de login
                LoginPageController loginController = loginLoader.getController();
                // Configurar qualquer informação necessária no controlador da página de login

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Botão "Leaderboard"
        Button leaderboardButton = (Button) root.lookup("#leaderboardButton");
        leaderboardButton.setOnAction(e -> {
            // Lógica para abrir a página de leaderboard
            System.out.println("Abrir leaderboard...");
        });

        // Botão "Definições"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Defenições.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 300);
        
        
        
        // Botão "Top 10"
        Button top10Button = (Button) root.lookup("#top10Button");
        top10Button.setOnAction(e -> {
            // Lógica para abrir a página de top 10
            System.out.println("Abrir top 10...");
        });

        // Botão "Sair"
        Button sairButton = (Button) root.lookup("#sairButton");
        sairButton.setOnAction(e -> {
            // Fechar o programa
            primaryStage.close();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}