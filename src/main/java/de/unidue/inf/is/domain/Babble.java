package de.unidue.inf.is.domain;

public class Babble {
    private String inhalt="";
    private String datum="";
    private String author="";
    private int likes=0;
    private int dislikes=0;

    private int shared=0;

    public Babble(String inhalt, String datum, String author,int likes,int dislikes, int shared){
        this.datum = datum;
        this.inhalt=inhalt;
        this.author=author;
        this.likes = likes;
        this.dislikes = dislikes;
        this.shared=shared;
    }

    public String getDatum() {
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
