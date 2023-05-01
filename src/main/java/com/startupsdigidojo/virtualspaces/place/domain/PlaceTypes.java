package com.startupsdigidojo.virtualspaces.place.domain;

import java.util.Arrays;
import java.util.Optional;

public enum PlaceTypes {

    PERSONAL_DESK("Personal Desk", "PERSONAL_DESK"),
    MEETING_ROOM("Meeting Room", "MEETING_ROOM"),
    BOARD("Board", "BOARD");

    private String fullnameType;
    private String nameType;

    PlaceTypes(String fullnameType, String nameType) {
        this.fullnameType = fullnameType;
        this.nameType = nameType;
    }

    public String getFullNameType() {
        return fullnameType;
    }

    //checks ignoring case that the name given is one of the possible values listed by this enum class
    public static Optional<PlaceTypes> byNameTypeIgnoreCase(String givenName) {
        return Arrays.stream(values()).filter(it -> (it.fullnameType.equalsIgnoreCase(givenName) || it.nameType.equalsIgnoreCase(givenName))).findAny();
    }

    //returns all the possible values accepted by this enum class
    public static String getStringValues() {
        return Arrays.toString(values());
    }
}
