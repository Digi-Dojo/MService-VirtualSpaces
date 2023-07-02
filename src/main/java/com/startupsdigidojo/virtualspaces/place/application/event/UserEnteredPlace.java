package com.startupsdigidojo.virtualspaces.place.application.event;

import com.startupsdigidojo.virtualspaces.place.domain.Place;
import lombok.Getter;

public class UserEnteredPlace {
    public static final String USER_ENTERS_PLACE = "User entered place";

    private Long userId;

    @Getter
    private String type = USER_ENTERS_PLACE;

    @Getter
    private Place payload;

    public UserEnteredPlace(Place place, long userId) {
        payload = place;
        this.userId = userId;
    }


    public String toJson() {
        return "{" +
                "\"type\": \"" + type + "\"," +
                "\"payload\": {" +
                "\"userId\": \"" + userId + "\"," +
                "\"uuid\": \"" + payload.getId() + "\"," +
                "\"type\": \"" + payload.getType() + "\"," +
                "\"startup\": \"" + payload.getStartupId() + "\"" +
                "\"time\": \"" + System.currentTimeMillis() + "\"" +
                "}" +
                "}";

    }
}
