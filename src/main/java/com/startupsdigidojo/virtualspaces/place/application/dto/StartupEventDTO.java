package com.startupsdigidojo.virtualspaces.place.application.dto;

import lombok.Getter;
import lombok.Setter;

public class StartupEventDTO {

    public static class StartupInfo{
        @Setter @Getter
        protected Long id;

        @Setter @Getter
        protected String name;

        @Setter @Getter
        protected String description;

    }

    @Setter @Getter
    protected String type;

    @Setter @Getter
    protected StartupInfo payload;

    @Override
    public String toString() {
        return "Startup Event{" +
                "type='" + type + '\'' +
                ", id=" + payload.id +
                ", name=" + payload.name +
                ", description=" + payload.description +
                '}';
    }
}
