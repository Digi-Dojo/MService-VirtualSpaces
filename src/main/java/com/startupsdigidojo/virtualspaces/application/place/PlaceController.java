package com.startupsdigidojo.virtualspaces.application.place;

import com.startupsdigidojo.virtualspaces.domain.place.ManagePlaces;
import com.startupsdigidojo.virtualspaces.domain.place.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/places")
public class PlaceController {

    private ManagePlaces managePlaces;

    @Autowired
    public PlaceController (ManagePlaces managePlaces) {
        this.managePlaces = managePlaces;
    }

    @GetMapping("{id}")
    public Place findById(@PathVariable("id") Long id) {
        return managePlaces.readPlace(id);
    }

    @PostMapping
    public Place createNewPlace(@RequestBody CreatePlaceDTO dto) {
        return managePlaces.createPlace(dto.getType(), dto.getStartupId());
    }

    @PostMapping
    public Place updatePlace(@RequestBody UpdatePlaceDTO dto) {
        return managePlaces.updatePlace(dto.getId(), dto.getType(), dto.getStartupId());
    }

    @DeleteMapping("{id}")
    public Place deletePlace(@PathVariable("id") Long id)  {
        return managePlaces.deletePlace(id);
    }

}
