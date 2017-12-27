package de.unidue.inf.is.domain;

public final class User {

    private String username;
    private String name;

    private String status;

    private String image_path;


    public User(String username) {
        this.username = username;
        name = " ";
        status = " ";
        image_path = " ";
    }

    public User(String username, String name, String Status, String image_path){
        this.username = username;
        this.name = name;
        this.status = Status;
        this.image_path = image_path;
    }

    public User() {

    }


    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setName (String name){
        this.name = name;

    }
    public void setStatus (String status){
        this.status = status;

    } public void setImage_path (String image_path){
        this.image_path = image_path;

    }

}