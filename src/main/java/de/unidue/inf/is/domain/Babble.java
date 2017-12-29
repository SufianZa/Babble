package de.unidue.inf.is.domain;

import java.sql.Timestamp;

public class Babble {
    private String inhalt="";
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

}
