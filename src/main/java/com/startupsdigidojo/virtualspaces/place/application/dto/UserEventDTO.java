package com.startupsdigidojo.virtualspaces.place.application.dto;

import lombok.Getter;
import lombok.Setter;

public class UserEventDTO {
    public static class UserInfo{
        @Setter
        @Getter
        protected Long id;

        @Setter @Getter
        protected String name;

        @Setter @Getter
        protected String mailAddress;

    }

    @Setter @Getter
    protected String type;

    @Setter @Getter
    protected UserEventDTO.UserInfo payload;

    @Override
    public String toString() {
        return "Startup Event{" +
                "type='" + type + '\'' +
                ", id=" + payload.id +
                ", name=" + payload.name +
                ", mailAddress=" + payload.mailAddress +
                '}';
    }
}
