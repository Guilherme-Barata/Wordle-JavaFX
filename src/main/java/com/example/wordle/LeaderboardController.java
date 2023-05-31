package com.example.wordle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {
    @FXML TableView<LeaderBoard> tvLeaderBoard;
    @FXML TableColumn<LeaderBoard, Integer> colRank;
    @FXML TableColumn<LeaderBoard, String> colNamePlayer;
    @FXML TableColumn<LeaderBoard, Integer> colGamesPlayed;
    @FXML TableColumn<LeaderBoard, Integer> colGamesWon;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            showPlayerList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<LeaderBoard> getPlayerList() throws Exception{
        ObservableList<LeaderBoard> playerList = FXCollections.observableArrayList();
        DBconnection db = new DBconnection();
        Connection connection = db.getConnection();

        String query = "SELECT * FROM users";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            LeaderBoard lb;
            int rank = 0;
            while (rs.next()) {
                rank++;
                lb = new LeaderBoard(rank, rs.getString("name"), rs.getInt("gamesplayed"), rs.getInt("gameswon"));
                playerList.add(lb);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerList;
    }

    public void showPlayerList() throws Exception{
        ObservableList<LeaderBoard> list = getPlayerList();

        colRank.setCellValueFactory(new PropertyValueFactory<LeaderBoard, Integer>("rank"));
        colNamePlayer.setCellValueFactory(new PropertyValueFactory<LeaderBoard, String>("nameplayer"));
        colGamesPlayed.setCellValueFactory(new PropertyValueFactory<LeaderBoard, Integer>("gamesplayed"));
        colGamesWon.setCellValueFactory(new PropertyValueFactory<LeaderBoard, Integer>("gameswon"));

        tvLeaderBoard.setItems(list);
    }

    public void handleBackbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appstage.setScene(scene);
        appstage.show();
    }
}
