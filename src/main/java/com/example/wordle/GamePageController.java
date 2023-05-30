package com.example.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GamePageController implements Initializable {

    private static int N; // game mode

    private static String lang; // language
    private int letterOrder; // control for the order of the letter
    private int lineOrder; // control for the order of the line
    private String solution;
    private boolean gameStatus; // to know if the game is over or not
    private int gamePlayed; // add game played
    private int gameWon; // add game won
    private int attempts; // add attempts needed
    @FXML
    private VBox vbox;
    @FXML
    private HBox line;
    @FXML
    private Label letter;

    @FXML
    private void HandleExitClick(ActionEvent event) throws IOException {

        // Switch to GamePage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameModePage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appstage.setScene(scene);
        appstage.show();
    }


    @FXML
    public void gameAlgorithm(){
        // Does the given answer have all the letters?
        if (letterOrder == N){
            // verify word
            String word = "";
            //String setColor = solution;
            char[] setColor = solution.toCharArray();

            line = (HBox) vbox.getChildren().get(lineOrder);
            for(int i = 0; i < N; i++){
                letter = (Label) line.getChildren().get(i);
                // verifies if its in the right position
                System.out.println(letter.getText().equalsIgnoreCase(String.valueOf(solution.charAt(i))));
                if (letter.getText().equalsIgnoreCase(String.valueOf(solution.charAt(i)))){
                    //setColor = setColor.replaceFirst(letter.getText(), "1");
                    setColor[i] = '1';
                    letter.setStyle("-fx-background-color: GREEN");
                }
                word += letter.getText();
            }
            // the answer given is right
            if (word.equalsIgnoreCase(solution)){
                lineOrder++;
                // set game status to false
                gameStatus = false;
                gamePlayed++;
                gameWon++;
                attempts = lineOrder;

                // Create Alert to inform Game Result
                Alert gameResult = new Alert(Alert.AlertType.NONE);
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                gameResult.setTitle("Game Summary");
                gameResult.setContentText("You Won :)\nAttempts: "+lineOrder);
                gameResult.getDialogPane().getButtonTypes().add(type);
                gameResult.show();
            }
            else {
                lineOrder++;
                for (int i = 0; i < N; i++){
                    letter = (Label) line.getChildren().get(i);
                    System.out.println(String.valueOf(setColor).toUpperCase());
                    System.out.println(letter.getText());
                    System.out.println(String.valueOf(setColor).toUpperCase().contains(letter.getText().toUpperCase()));
                    if (String.valueOf(setColor).toUpperCase().contains(letter.getText().toUpperCase())){
                        letter.setStyle("-fx-background-color: YELLOW");
                    }
                    else {
                        if (!(String.valueOf(setColor).charAt(i) == '1')) {
                            letter.setStyle("-fx-background-color: GRAY");
                        }
                    }
                }
                letterOrder = 0;
                if (lineOrder == N) {
                    // Max attempts reached
                    // Set game status to false
                    gameStatus = false;
                    gamePlayed++;
                    // Create Alert to inform Game Result
                    Alert gameResult = new Alert(Alert.AlertType.NONE);
                    ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    gameResult.setTitle("Game Summary");
                    gameResult.setContentText("You Lost :(\nThe word was "+solution.toUpperCase()+"!");
                    gameResult.getDialogPane().getButtonTypes().add(type);
                    gameResult.show();
                }
            }
        }
    }
    @FXML
    public void HandleButtonCLick(ActionEvent event) {
        if(event.getSource() instanceof Button){
            String btnValue = ((Button) event.getSource()).getText();

            if (gameStatus) {
                if (btnValue.equalsIgnoreCase("Enter")) {
                    gameAlgorithm();
                } else {
                    line = (HBox) vbox.getChildren().get(lineOrder);
                    if (btnValue.equalsIgnoreCase("Delete")) {
                        // Erase letter
                        if (letterOrder > 0) {
                            letterOrder--;
                            letter = (Label) line.getChildren().get(letterOrder);
                            letter.setText("");
                        }
                    }
                    else {
                        // The Button/key pressed isnt neither the 'Enter' or the 'Delete'
                        if (lineOrder < N) {
                            if (letterOrder < N) {
                                letter = (Label) line.getChildren().get(letterOrder);
                                letter.setText(btnValue);
                                letterOrder++;
                            }
                        }
                    }
                }
            }
        }
    }


    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode().isLetterKey() || event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.BACK_SPACE){
            KeyCode lastPressedKey = event.getCode();

            if (gameStatus) {
                if (lastPressedKey.toString().equalsIgnoreCase("Enter")) {
                    gameAlgorithm();
                } else {
                    line = (HBox) vbox.getChildren().get(lineOrder);
                    if (lastPressedKey.toString().equalsIgnoreCase("BACK_SPACE")) {
                        // Erase letter
                        if (letterOrder > 0) {
                            letterOrder--;

                            letter = (Label) line.getChildren().get(letterOrder);
                            letter.setText("");
                        }
                    }
                    else {
                        // The key pressed isnt neither the 'Enter' or the 'Delete'
                        if (lineOrder < N) {
                            if (letterOrder < N) {
                                letter = (Label) line.getChildren().get(letterOrder);
                                letter.setText(lastPressedKey.toString());
                                letterOrder++;
                            }
                        }
                    }
                }
            }
        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        letterOrder = 0;
        lineOrder = 0;
        gameStatus = true;
        gamePlayed = 0;
        gameWon = 0;
        attempts = 0;

        // get player_id in order to get stats and set stats at the end of the game
        Profile player = Profile.getInstance();

        // get numberOfLetters in order to setup the game environment
        GameMode game = GameMode.getInstance();
        N = game.getNumberOfLetters();

        // setup game environment
        for(int i = 0; i < N; i++){
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.setSpacing(5);
            for(int j = 0; j < N; j++) {
                Label label = new Label("");
                label.setFont(Font.font(34));
                label.setAlignment(Pos.CENTER);
                label.setPrefWidth(40);
                label.setPrefHeight(40);
                label.setStyle("-fx-border-color: BLACK");
                hbox.getChildren().add(label); // Add label to hbox
            }
            vbox.getChildren().add(hbox); // Add hbox to vbox
        }

        // Get language in order to set up word
        Language language = Language.getInstance();
        lang = language.getLang();

        // Get word to discover
        String fileName = N + ".txt";
        String filePath = "src/main/resources/" + lang + "/" + fileName;

        try {
            File file = new File(filePath);
            int counter = 0;

            // count number of lines
            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextLine()) {
                    reader.nextLine();
                    counter++;
                }
            }

            // get random line from the available
            int randomLine = (int) (Math.random() * counter) + 1;

            // read file again but save the word from the line randomized
            counter = 0;
            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextLine()) {
                    counter++;
                    if (counter == randomLine) {
                        solution = reader.nextLine();
                        System.out.println(solution);
                        break;
                    }
                    reader.nextLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // game wont work, exit application
            System.exit(1);
        }

    }
}
