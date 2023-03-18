package com.startupsdigidojo.virtualspaces.place.domain;

import java.util.Arrays;
import java.util.Optional;

public enum PlaceTypes {

    PERSONAL_DESK("Personal Desk"),
    MEETING_ROOM("Meeting Room"),
    BOARD("Board");

    private String fullnameType;

    PlaceTypes(String fullnameType) {
        this.fullnameType = fullnameType;
    }

    public String getFullNameType() {
        return fullnameType;
    }

    public static Optional<PlaceTypes> byNameTypeIgnoreCase(String givenName) {
        return Arrays.stream(values()).filter(it -> it.name().equalsIgnoreCase(givenName)).findAny();
    }

    public static String getStringValues() {
        return values().toString();
    }
}
