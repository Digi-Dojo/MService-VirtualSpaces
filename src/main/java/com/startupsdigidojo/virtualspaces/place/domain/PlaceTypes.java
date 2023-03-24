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

    //checks ignoring case that the name given is one of the possible values listed by this enum class
    public static Optional<PlaceTypes> byNameTypeIgnoreCase(String givenName) {
        return Arrays.stream(values()).filter(it -> it.fullnameType.equalsIgnoreCase(givenName)).findAny();
    }

    //returns all the possible values accepted by this enum class
    public static String getStringValues() {
        return Arrays.toString(values());
    }
}
