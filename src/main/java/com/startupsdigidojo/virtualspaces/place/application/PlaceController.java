package com.startupsdigidojo.virtualspaces.place.application;

import com.startupsdigidojo.virtualspaces.place.domain.ManagePlaces;
import com.startupsdigidojo.virtualspaces.place.domain.Place;
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

    @GetMapping("/{id}")
    public Place findById(@PathVariable("id") Long id) {
        return managePlaces.readPlace(id);
    }

    @PostMapping("/create")
    public Place createNewPlace(@RequestBody CreatePlaceDTO dto) {
        return managePlaces.createPlace(dto.getType(), dto.getStartupId());
    }

    @PostMapping("/update")
    public Place updatePlace(@RequestBody UpdatePlaceDTO dto) {
        return managePlaces.updatePlace(dto.getId(), dto.getType(), dto.getStartupId());
    }

    @DeleteMapping("/delete/{id}")
    public Place deletePlace(@PathVariable("id") Long id)  {
        return managePlaces.deletePlace(id);
    }

}
