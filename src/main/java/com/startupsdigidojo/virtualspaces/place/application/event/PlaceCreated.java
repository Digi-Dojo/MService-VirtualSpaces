package com.startupsdigidojo.virtualspaces.place.application.event;

import com.startupsdigidojo.virtualspaces.place.domain.Place;
import lombok.Getter;

public class PlaceCreated extends PlaceEvent {
    public static final String PLACE_CREATED = "Place created";

    @Getter
    private String type = PLACE_CREATED;

    @Getter
    private Place payload;

    public PlaceCreated(Place place) {
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
