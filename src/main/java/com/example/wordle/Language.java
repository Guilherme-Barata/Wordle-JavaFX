package com.example.wordle;

public class Language {

    private String lang; // en, fr, pt
    private final static Language INSTANCE = new Language();

    public Language(){}

    public static Language getInstance(){
        return INSTANCE;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }
}
