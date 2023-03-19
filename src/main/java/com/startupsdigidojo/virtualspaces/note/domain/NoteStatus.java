package com.startupsdigidojo.virtualspaces.note.domain;

import com.startupsdigidojo.virtualspaces.place.domain.PlaceTypes;

import java.util.Arrays;
import java.util.Optional;

public enum NoteStatus {

    ADDED,
    REMOVED;

    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    Optional<NoteStatus> byNameTypeIgnoreCase(String givenStatus) {
        return Arrays.stream(values()).filter(it -> it.statusName.equalsIgnoreCase(givenStatus)).findAny();
    }

    //returns all the possible values accepted by this enum class
    public static String getStringValues() {
        return Arrays.toString(values());
    }
}
