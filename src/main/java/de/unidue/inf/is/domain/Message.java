package de.unidue.inf.is.domain;

import java.sql.Timestamp;

public class Message implements Comparable<Message>{
    private int id;
    private String text;
    private String sender;
    private String recipient;
    private Timestamp created;

    public Message(int id,String text, String sender, String recipient,Timestamp created){
        this.created = created;
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
        this.created = created;
        this.id=id;
    }
    public int getId() {
        return id;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int compareTo(Message m) {
        return created.compareTo(m.getCreated());
    }

}
