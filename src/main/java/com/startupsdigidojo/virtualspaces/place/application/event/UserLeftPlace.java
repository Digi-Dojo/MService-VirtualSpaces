package com.startupsdigidojo.virtualspaces.place.application.event;

import com.startupsdigidojo.virtualspaces.place.domain.Place;
import lombok.Getter;

public class UserLeftPlace {

    public static final String USER_LEAVES_PLACE = "User left the place";

    private Long userId;

    @Getter
    private String type = USER_LEAVES_PLACE;

    @Getter
    private Place payload;

    public UserLeftPlace(Place place, Long userId) {
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
