package com.example.wordle;

public class LeaderBoard {
    private int rank;
    private String nameplayer;
    private int gamesplayed;
    private int gameswon;

    public LeaderBoard(int rank, String nameplayer, int gamesplayed, int gameswon) {
        this.rank = rank;
        this.nameplayer = nameplayer;
        this.gamesplayed = gamesplayed;
        this.gameswon = gameswon;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getNameplayer() {
        return nameplayer;
    }

    public void setNameplayer(String nameplayer) {
        this.nameplayer = nameplayer;
    }

    public int getGamesplayed() {
        return gamesplayed;
    }

    public void setGamesplayed(int gamesplayed) {
        this.gamesplayed = gamesplayed;
    }

    public int getGameswon() {
        return gameswon;
    }

    public void setGameswon(int gameswon) {
        this.gameswon = gameswon;
    }
}
