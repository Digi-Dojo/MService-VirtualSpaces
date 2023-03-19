package com.startupsdigidojo.virtualspaces.note.domain;

import jakarta.persistence.*;

// NOTE: Has a small text added by a user in a place. Should have a date and a status (ADDED, REMOVED).

@Entity
public class Note {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Long startupId;

    public Note () {}

    public Note(Long startupId) {
        this.startupId = startupId;
    }

    public Note(Long id, String text, Long startupId) {
        this.id = id;
        this.text = text;
        this.startupId = startupId;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getStartupId() {
        return startupId;
    }

    public void setStartupId(Long startupId) {
        this.startupId = startupId;
    }
}

