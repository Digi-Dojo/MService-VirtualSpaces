package com.startupsdigidojo.virtualspaces.place.application.event;

import lombok.Getter;
import lombok.Setter;

public abstract class PlaceEvent {
    @Setter
    @Getter
    protected String type;

    @Setter
    @Getter
    protected Object payload;

}
