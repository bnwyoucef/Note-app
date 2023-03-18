package com.bounoua.note_app;

import java.io.Serializable;

public class Note implements Serializable {
    private String title;
    private String description;
    private String noteDate;

    public String getTitle() {
        return title;
    }

    public Note(String title, String description, String noteDate) {
        this.title = title;
        this.description = description;
        this.noteDate = noteDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }
}
