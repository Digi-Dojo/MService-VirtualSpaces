package com.startupsdigidojo.virtualspaces.place.application;

import com.startupsdigidojo.virtualspaces.place.domain.ManagePlaces;
import com.startupsdigidojo.virtualspaces.place.domain.Place;
import com.startupsdigidojo.virtualspaces.place.domain.PlaceTypes;
import com.startupsdigidojo.virtualspaces.place.domain.SearchPlaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/v1/places")
public class PlaceController {

    private ManagePlaces managePlaces;
    private SearchPlaces searchPlaces;

    @Autowired
    public PlaceController (ManagePlaces managePlaces, SearchPlaces searchPlaces) {
        this.managePlaces = managePlaces;
        this.searchPlaces = searchPlaces;
    }

    @GetMapping("/{id}")
    public Place findById(@PathVariable("id") Long id) {
        return managePlaces.readPlace(id);
    }

    @PostMapping("/create")
    public Place createNewPlace(@RequestBody CreatePlaceDTO dto) {
        return managePlaces.createPlace(dto.getType(), dto.getStartupId());
    }

    @PostMapping("/update/{id}")
    public Place updatePlace(@PathVariable("id") Long id, @RequestBody UpdatePlaceDTO dto) {
        return managePlaces.updatePlace(id, dto.getType(), dto.getStartupId());
    }

    @DeleteMapping("/delete/{id}")
    public Place deletePlace(@PathVariable("id") Long id)  {
        return managePlaces.deletePlace(id);
    }

    @GetMapping("/getAll")
    public List<Place> findAll(){
        return searchPlaces.findAll();
    }

    @GetMapping("/getFromType/{type}")
    public List<Place> findByType(@PathVariable("type")PlaceTypes type) {
        return searchPlaces.findByType(type);
    }

    @GetMapping("/{id}/type")
    public PlaceTypes findPlaceType(@PathVariable("id") Long id) {
        return managePlaces.readPlace(id).getType();
    }

}
