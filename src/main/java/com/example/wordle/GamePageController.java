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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {

    private static int N;
    private int letterOrder; // control for the order of the letter
    private int lineOrder; // control for the order of the line
    private String solution = "yeah";
    private boolean gameStatus; // to know if the game is over or not
    @FXML
    private VBox vbox;
    @FXML
    private HBox line;
    @FXML
    private Label letter;

    @FXML
    private void HandleExitClick() {
        System.exit(0);
    }

    @FXML
    public void HandleButtonCLick(ActionEvent event) {
        if(event.getSource() instanceof Button){
            String btnValue = ((Button) event.getSource()).getText();
            System.out.println(btnValue);

            if (gameStatus) {
                if (btnValue.equalsIgnoreCase("Enter")) {
                    if (letterOrder == N){
                        // verify word
                        String word = "";
                        String setColor = solution;
                        for (int i = 0; i < N; i++) {
                            line = (HBox) vbox.getChildren().get(lineOrder);
                            letter = (Label) line.getChildren().get(i);
                            System.out.println("setColor: "+setColor);
                            System.out.println("Letter: "+letter.getText());
                            System.out.println(setColor.toUpperCase().contains(letter.getText().toUpperCase()));
                            // The letter exists
                            if (setColor.toUpperCase().contains(letter.getText().toUpperCase())) {
                                System.out.println("The letter exists");
                                // The letter is in the right place
                                if(letter.getText().equalsIgnoreCase(String.valueOf(solution.charAt(i)))) {
                                    letter.setStyle("-fx-background-color: GREEN");
                                    String aux = setColor.replace(String.valueOf(letter.getText().toLowerCase()), "");
                                    setColor = aux.toString();
                                }
                                else { // The letter isnt in the right place
                                    letter.setStyle("-fx-background-color: YELLOW");
                                    String aux = setColor.replace(String.valueOf(letter.getText().toLowerCase()), "");
                                    setColor = aux.toString();
                                }
                            }
                            else {
                                letter.setStyle("-fx-background-color: GRAY");
                                String aux = setColor.replace(String.valueOf(letter.getText().toLowerCase()), "");
                                setColor = aux.toString();
                            }
                            word += letter.getText();
                        }
                        lineOrder++;
                        if (word.equalsIgnoreCase(solution)) {
                            // set game status to false
                            gameStatus = false;

                            // Create Alert to inform Game Result
                            Alert gameResult = new Alert(Alert.AlertType.NONE);
                            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                            gameResult.setTitle("Game Summary");
                            gameResult.setContentText("You Won :)\nAttempts: "+lineOrder);
                            gameResult.getDialogPane().getButtonTypes().add(type);
                            gameResult.show();
                            //lineOrder = N + 1;
                        }
                        else {
                            word = "";
                            letterOrder = 0;
                            if (lineOrder == N) {
                                // Max attempts reached
                                // Set game status to false
                                gameStatus = false;
                                // Create Alert to inform Game Result
                                Alert gameResult = new Alert(Alert.AlertType.NONE);
                                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                gameResult.setTitle("Game Summary");
                                gameResult.setContentText("You Lost :(");
                                gameResult.getDialogPane().getButtonTypes().add(type);
                                gameResult.show();
                            }
                        }
                    }
                } else {
                    if (btnValue.equalsIgnoreCase("Delete")) {
                        // Erase letter
                        if (letterOrder > 0) {
                            letterOrder--;
                            line = (HBox) vbox.getChildren().get(lineOrder);
                            letter = (Label) line.getChildren().get(letterOrder);
                            letter.setText("");
                        }
                    }
                    else {
                        // The Button/key pressed isnt neither the 'Enter' or the 'Delete'
                        if (lineOrder < N) {
                            line = (HBox) vbox.getChildren().get(lineOrder);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        letterOrder = 0;
        lineOrder = 0;
        gameStatus = true;

        // Get numberOfLetters in order to setup the game environment
        GameMode game = GameMode.getInstance();
        N = game.getNumberOfLetters();

        // Setup game environment
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
    }
}
