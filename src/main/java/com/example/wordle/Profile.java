package com.example.wordle;

public class Profile {

    private int player_id;

    private final static Profile INSTANCE = new Profile();

    public Profile() {}

    public static Profile getInstance() {
        return INSTANCE;
    }
    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public int getPlayer_id(){
        return player_id;
    }
}
