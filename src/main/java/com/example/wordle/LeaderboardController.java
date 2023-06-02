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
import javafx.scene.control.TableRow;
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
    private String player_id;

    @FXML TableView<LeaderBoard> tvPlayerStats;
    @FXML TableColumn<LeaderBoard, String> colNamePlayerStats;
    @FXML TableColumn<LeaderBoard, Integer> colGamesPlayedStats;
    @FXML TableColumn<LeaderBoard, Integer> colGamesWonStats;
    @FXML TableView<LeaderBoard> tvLeaderBoard;
    @FXML TableColumn<LeaderBoard, Integer> colRank;
    @FXML TableColumn<LeaderBoard, String> colNamePlayer;
    @FXML TableColumn<LeaderBoard, Integer> colGamesPlayed;
    @FXML TableColumn<LeaderBoard, Integer> colGamesWon;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        tvLeaderBoard.setFixedCellSize(25); // Altura fixa das células
        tvLeaderBoard.prefHeightProperty().bind(tvLeaderBoard.fixedCellSizeProperty().multiply(11)); // Altura total da TableView
        tvLeaderBoard.minHeightProperty().bind(tvLeaderBoard.prefHeightProperty());
        tvLeaderBoard.maxHeightProperty().bind(tvLeaderBoard.prefHeightProperty());

        tvPlayerStats.setFixedCellSize(25); // Altura fixa das células
        tvPlayerStats.prefHeightProperty().bind(tvPlayerStats.fixedCellSizeProperty().multiply(2.5)); // Altura total da TableView
        tvPlayerStats.minHeightProperty().bind(tvPlayerStats.prefHeightProperty());
        tvPlayerStats.maxHeightProperty().bind(tvPlayerStats.prefHeightProperty());

        try {
            showPlayer();
            showPlayerList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<LeaderBoard> getPlayerList() throws Exception{
        ObservableList<LeaderBoard> playerList = FXCollections.observableArrayList();
        DBconnection db = new DBconnection();
        Connection connection = db.getConnection();

        String query = "SELECT * FROM users ORDER BY gameswon DESC";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            LeaderBoard lb;

            int rank = 0;

            while (rs.next() && rank !=10) {
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

        DBconnection db = new DBconnection();
        Connection connection = db.getConnection();
        player_id = LoginPageController.IDvalue;

        String query = "SELECT name FROM users WHERE id = " + player_id;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()){
                String NameLogged = rs.getString("name");

                tvLeaderBoard.setRowFactory(tv -> new TableRow<LeaderBoard>() {
                    @Override
                    public void updateItem(LeaderBoard item, boolean empty) {
                        super.updateItem(item, empty) ;
                        if (item == null) {
                            setStyle("");
                        } else if (item.getNameplayer().equals(NameLogged)) {
                            setStyle("-fx-background-color: #fffee0;");
                        } else {
                            setStyle("");
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvLeaderBoard.setItems(list);
    }

    public ObservableList<LeaderBoard> getPlayer() throws Exception{
        ObservableList<LeaderBoard> player = FXCollections.observableArrayList();
        DBconnection db = new DBconnection();
        Connection connection = db.getConnection();

        player_id = LoginPageController.IDvalue;

        String query = "SELECT name, gamesplayed, gameswon FROM users WHERE id = " + player_id;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            LeaderBoard lb;

            int rank = 0;

            if (rs.next()) {
                lb = new LeaderBoard(rank, rs.getString("name"), rs.getInt("gamesplayed"), rs.getInt("gameswon"));
                player.add(lb);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return player;
    }

    public void showPlayer() throws Exception{
        ObservableList<LeaderBoard> player = getPlayer();

        colNamePlayerStats.setCellValueFactory(new PropertyValueFactory<LeaderBoard, String>("nameplayer"));
        colGamesPlayedStats.setCellValueFactory(new PropertyValueFactory<LeaderBoard, Integer>("gamesplayed"));
        colGamesWonStats.setCellValueFactory(new PropertyValueFactory<LeaderBoard, Integer>("gameswon"));

        tvPlayerStats.setItems(player);
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
