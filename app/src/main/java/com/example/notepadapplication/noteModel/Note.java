package com.example.notepadapplication.noteModel;

public class Note {
    private int id;
    private String title;
    private String date;
    private  String body;

    public Note(int id, String title, String date, String body) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public Note(String title, String date, String body) {
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
