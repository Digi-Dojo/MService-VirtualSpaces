package com.startupsdigidojo.virtualspaces.place.application.event;

import com.startupsdigidojo.virtualspaces.place.domain.Place;
import lombok.Getter;

public class UserLeftPlace {

    public static final String USER_LEAVES_PLACE = "User left the place";

    @Getter
    private String type = USER_LEAVES_PLACE;

    @Getter
    private Place payload;

    public UserLeftPlace(Place place) {
        payload = place;
    }

    public String toJson() {
        return "{" +
                "\"type\": \"" + type + "\"," +
                "\"payload\": {" +
                "\"uuid\": \"" + payload.getId() + "\"," +
                "\"type\": \"" + payload.getType() + "\"," +
                "\"startup\": \"" + payload.getStartupId() + "\"" +
                "\"time\": \"" + System.currentTimeMillis() + "\"" +
                "}" +
                "}";
    }
}
