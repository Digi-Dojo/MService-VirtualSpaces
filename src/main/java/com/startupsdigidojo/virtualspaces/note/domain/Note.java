package com.startupsdigidojo.virtualspaces.note.domain;

import com.startupsdigidojo.virtualspaces.place.domain.Place;
import jakarta.persistence.*;

// NOTE: Has a small text added by a user in a place. Should have a date and a status (ADDED, REMOVED).

@Entity
public class Note {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Long placeId;

    public Note () {}

    public Note(String text, Long placeId) {
        this.text = text;
        this.placeId = placeId;
    }

    public Note(Long id, String text, Long placeId) {
        this.id = id;
        this.text = text;
        this.placeId = placeId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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
}

