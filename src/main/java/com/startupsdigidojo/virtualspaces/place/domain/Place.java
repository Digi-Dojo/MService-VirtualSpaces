package com.startupsdigidojo.virtualspaces.place.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Place {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private PlaceTypes type;

    private Long startupId;

    @ElementCollection
    private List<Long> users;

    public Place () {}

    public Place(PlaceTypes type, Long startupId) {
        this.type = type;
        this.startupId = startupId;
        this.users = new ArrayList<>();
    }

    public Place(Long id, PlaceTypes type, Long startupId) {
        this.id = id;
        this.type = type;
        this.startupId = startupId;
        this.users = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public PlaceTypes getType() {
        return type;
    }

    public void setType(PlaceTypes type) {
        this.type = type;
    }

    public Long getStartupId() {
        return startupId;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void addUser(Long userId) {
        this.users.add(userId);
    }

    public void removeUser(Long userId) {
        if(this.users.contains(userId)) {
            users.remove(userId);
        }
    }

    public void setStartupId(Long startupId) {
        this.startupId = startupId;
    }
}
