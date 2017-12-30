package de.unidue.inf.is.domain;

import java.sql.Timestamp;

public class Babble {
    private String inhalt="";
    private String activity="";
    private Timestamp datum;
    private String author="";
    private int id = 0;
    private int likes=0;
    private int dislikes=0;
    private int shared=0;

    public Babble(int id, String inhalt, Timestamp datum, String author,int likes,int dislikes, int shared){
        this.id= id;
        this.datum = datum;
        this.inhalt=inhalt;
        this.author=author;
        this.likes = likes;
        this.dislikes = dislikes;
        this.shared=shared;
    }


    public String getActivity() {
        return activity;
    }
    public Timestamp getDatum() {
        return datum;
    }

    public String getInhalt() {
        return inhalt;
    }

    public String getAuthor() {
        return author;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getLikes() {
        return likes;
    }

    public int getShared() {
        return shared;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setShared(int shared) {
        this.shared = shared;
    }

    public String timestampasstring(){
        return datum.toString();
    }

}

