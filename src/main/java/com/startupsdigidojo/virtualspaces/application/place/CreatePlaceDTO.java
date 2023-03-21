package com.startupsdigidojo.virtualspaces.application.place;

public class CreatePlaceDTO {

    private String type;
    private Long startupId;

    public CreatePlaceDTO () {}

    public CreatePlaceDTO (String type, Long startupId) {
        this.type = type;
        this.startupId = startupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getStartupId() {
        return startupId;
    }

    public void setStartupId(Long startupId) {
        this.startupId = startupId;
    }
}
