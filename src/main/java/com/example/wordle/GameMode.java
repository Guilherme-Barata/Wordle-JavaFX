package com.example.wordle;

public class GameMode {

    private int numberOfLetters;
    private final static GameMode INSTANCE = new GameMode();

    private GameMode(){}

    public static GameMode getInstance(){
        return INSTANCE;
    }

    public void setNumberOfLetters(int n){
        numberOfLetters = n;
    }

    public int getNumberOfLetters(){
        return numberOfLetters;
    }
}
