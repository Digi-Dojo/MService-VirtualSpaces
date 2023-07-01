package com.startupsdigidojo.virtualspaces.note.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

// NOTE: Has a small text added by a user in a place. Should have a date and a status (ADDED, REMOVED).

@Entity
public class Note {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Long placeId;

@JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    private boolean statusAdded;

    public Note () {}

    public Note(String text, Long placeId, Date date, boolean statusAdded) {
        this.text = text;
        this.placeId = placeId;
        this.date = date;
        this.statusAdded = statusAdded;
    }

    public Note(Long id, String text, Long placeId, Date date, boolean statusAdded) {
        this.id = id;
        this.text = text;
        this.placeId = placeId;
        this.date = date;
        this.statusAdded = statusAdded;
    }

    public Long getId() {
        return id;
    }

    public boolean getStatusAdded() {
        return this.statusAdded;
    }

    public void setStatusAdded (boolean statusAdded) { this.statusAdded = statusAdded; }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {

     return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

