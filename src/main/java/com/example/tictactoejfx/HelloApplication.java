package com.example.tictactoejfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static int[][] boardArr = new int[3][3];
    public static Button[][] buttonArr = new Button[3][3];
    public static boolean turn = false;
    int player1 =1;
    int player2 =2;
    int player1_wins =0;
    int player2_wins =0;
    public static boolean hasWinner = false;
    public static boolean gameRunning = true;
    public static int turns = 0;



    @Override
    public void start(Stage stage) throws IOException {

        VBox root = new VBox(10);

        GridPane grid = new GridPane();
        Button buttonReset = new Button("Reset");
        Label labelGame = new Label("Player 1's Turn");


        HBox labelHbox = new HBox();
        labelHbox.getChildren().addAll(labelGame);
        labelHbox.setAlignment(Pos.CENTER);

        HBox resetButtonHbox = new HBox();
        resetButtonHbox.getChildren().addAll(buttonReset);
        resetButtonHbox.setAlignment(Pos.CENTER);

        VBox vBoxScore = new VBox();
        Label labelScores = new Label("Wins");
        Label labelPlayer1Score = new Label("Player 1: ");
        Label labelPlayer2Score = new Label("Player 2: ");

        vBoxScore.getChildren().addAll(labelScores,labelPlayer1Score,labelPlayer2Score);




        for (int row =0; row < 3; row++) {
            for(int col =0; col < 3; col ++) {


                Button cell = new Button();
                buttonArr[col][row] = cell;
                cell.setPrefSize(80,80);

                int Row = row;
                int Col = col;

                grid.add(cell, Col, Row);


                cell.setOnAction(actionEvent -> {
                    turn = !turn;

                    if (turn) {
                        labelGame.setText("Player 1's Turn");
                        cell.setText("O");
                        boardArr[Col][Row] = 1;
                    } else {
                        labelGame.setText("Player 2's Turn");
                        cell.setText("X");
                        boardArr[Col][Row] = 2;
                    }
                    cell.setDisable(true);
                    for (int row1 =0; row1 < 3; row1++) {
                        for(int col1 =0; col1 < 3; col1 ++) {
                            System.out.print(boardArr[col1][row1]);
                        }
                        System.out.println();
                    }
                    turns+=1;
                    System.out.println("Turn: " + turns);
                    if (turns >= 9) {
                        labelGame.setText("Draw...");
                        buttonReset.setText("Play Again");
                    }

                    if (turn) {
                        if (hasWon(1)) {
                            System.out.println("Player 1 wins");
                            labelGame.setText("Player 1 wins!");
                            player1_wins+=1;
                            labelPlayer1Score.setText("Player 1: " + player1_wins);
                            buttonReset.setText("Play Again");
                            lockBoard();
                        }
                    } else {
                        if (hasWon(2)) {
                            System.out.println("Player 2 wins");
                            labelGame.setText("Player 2 wins!");
                            player2_wins+=1;
                            labelPlayer2Score.setText("Player 2: " + player2_wins);
                            buttonReset.setText("Play Again");
                            lockBoard();
                        }
                    }
                });

            }
        }

        grid.setAlignment(Pos.CENTER);
        root.getChildren().addAll(labelHbox,grid,resetButtonHbox,vBoxScore);
        Scene scene = new Scene(root, 300, 300);
        //scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);

        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();

        buttonReset.setOnAction(actionEvent -> {
            System.out.println("reset");
            resetBoard();
        });
    }

    public static void main(String[] args) {
        launch();


    }

    public static boolean hasWon(int player) {
        //checks all three of the rows
        for (int row =0; row < 3; row ++) {
            if (
                    boardArr[row][0] == player &&
                    boardArr[row][1] == player &&
                    boardArr[row][2] == player
            )return true;
            }

        //checks all 3 of the columns
        for (int col =0; col < 3; col ++) {
            if (
                    boardArr[0][col] == player &&
                    boardArr[1][col] == player &&
                    boardArr[2][col] == player
            ) return true;
        }

        //checking corners top left --> bottom right
        for (int i =0; i < 3; i++) {
            if (
                    boardArr[0][0] == player &&
                    boardArr[1][1] == player &&
                    boardArr[2][2] == player

            ) return true;
        }

        //checking corners top left --> bottom right
        for (int i =0; i < 3; i++) {
            if (
                    boardArr[0][2] == player &&
                    boardArr[1][1] == player &&
                    boardArr[2][0] == player
            ) return true;
        }

        return false;
        }

        public void lockBoard() {
            for (int i =0; i < 3; i++) {
                for (int j =0; j < 3; j++) {

                    buttonArr[i][j].setDisable(true);

                }
            }

        }

        public void resetBoard() {

        for (int i =0; i < 3; i++) {
            for (int j =0; j < 3; j++) {
                buttonArr[i][j].setText(" ");
                buttonArr[i][j].setDisable(false);
                boardArr[i][j] = 0;
                turns = 0;
            }
        }

        }
}