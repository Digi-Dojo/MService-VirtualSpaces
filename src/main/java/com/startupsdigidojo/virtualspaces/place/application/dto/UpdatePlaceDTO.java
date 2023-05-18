package com.startupsdigidojo.virtualspaces.place.application.dto;

import com.startupsdigidojo.virtualspaces.place.application.dto.CreatePlaceDTO;

public class UpdatePlaceDTO extends CreatePlaceDTO {

    private Long id;

    public UpdatePlaceDTO () {super(); }

    public UpdatePlaceDTO (Long id, String type, Long startupId) {
        super(type, startupId);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
