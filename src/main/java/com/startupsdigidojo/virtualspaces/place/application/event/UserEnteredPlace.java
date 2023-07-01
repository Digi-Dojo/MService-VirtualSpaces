package com.startupsdigidojo.virtualspaces.place.application.event;

import com.startupsdigidojo.virtualspaces.place.domain.Place;
import lombok.Getter;

public class UserEnteredPlace {
    public static final String USER_ENTERS_PLACE = "User entered place";

    @Getter
    private String type = USER_ENTERS_PLACE;

    @Getter
    private Place payload;

    public UserEnteredPlace(Place place) {
        payload = place;
    }

    public String toJson() {
        return "{" +
                "\"userId\": \"" +"\"," +
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
