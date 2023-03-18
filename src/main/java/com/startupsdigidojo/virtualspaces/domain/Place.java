package com.startupsdigidojo.virtualspaces.domain;

import jakarta.persistence.*;

@Entity
public class Place {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private Long startupId;

    public Place () {}

    public Place(String type, Long startupId) {
        this.type = type;
        this.startupId = startupId;
    }

    public Place(Long id, String type, Long startupId) {
        this.id = id;
        this.type = type;
        this.startupId = startupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getStartupId() {
        return startupId;
    }

    public void setStartupId(Long startupId) {
        this.startupId = startupId;
    }
}
