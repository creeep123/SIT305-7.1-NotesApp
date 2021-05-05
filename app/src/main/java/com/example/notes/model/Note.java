package com.example.notes.model;

public class Note {

    private int note_id;
    private String note_description;

    public Note(int note_id, String note_description) {
        this.note_id = note_id;
        this.note_description = note_description;
    }

    public Note(String note_description)
    {
        this.note_description = note_description;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getNote_description() {
        return note_description;
    }

    public void setNote_description(String note_description) {
        this.note_description = note_description;
    }
}
