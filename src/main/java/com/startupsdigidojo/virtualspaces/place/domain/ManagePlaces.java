package com.startupsdigidojo.virtualspaces.place.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagePlaces {

    private PlaceRepository placeRepository;
    private SearchPlaces searchPlaces;
    @Autowired
    public ManagePlaces(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.searchPlaces = searchPlaces;
    }

    //checks that the type is a valid type among those specified by the PlaceTypes class
    private PlaceTypes validatePlaceType (String type) {

        Optional<PlaceTypes> maybeTypePlace = PlaceTypes.byNameTypeIgnoreCase(type);

        if(maybeTypePlace.isEmpty())
            throw new IllegalArgumentException("Place type '" + type + "' is not a valid type. " +
                    "Accepted types are: " + PlaceTypes.getStringValues());

        return maybeTypePlace.get();
    }

    //checks that the given id takes to a valid entity Place
    private Place validatePlace (Long id) {

        Optional<Place> maybePlace = placeRepository.findById(id);

        if(maybePlace.isEmpty())
            throw new IllegalArgumentException("Place with id '" + id + "' does not exist yet!");

        return maybePlace.get();
    }

    public Place createPlace(String type, Long startupId) {

        PlaceTypes typePlace = validatePlaceType(type);

        return placeRepository.save(new Place(typePlace, startupId));
    }

    public Place readPlace(Long id) {

        Place place = validatePlace(id);

        return place;

    }

    public Place addUser (Long id, Long userId) {
        Place place = readPlace(id);
        if(!place.getUsers().contains(userId)) {
            place.addUser(userId);
        } else {
            throw new IllegalArgumentException("User "+ userId+ " is already in this place");
        }
        return placeRepository.save(place);
    }

    public Place removeUser (Long id, Long userId) {
        Place place = readPlace(id);
        if(place.getUsers().contains(userId)) {
            place.removeUser(userId);
        } else {
            throw new IllegalArgumentException("User "+ userId+ " not found in Place #" + id);
        }
        return placeRepository.save(place);
    }

    public Place updatePlace (Long id, String type, Long startupId) {

        Place place = validatePlace(id);

        PlaceTypes typePlace = validatePlaceType(type);

        place.setType(typePlace);
        place.setStartupId(startupId);


        return placeRepository.save(place);
    }

    public Place deletePlace (Long id) {

        Place place = validatePlace(id);

        placeRepository.delete(place);

        return place;
    }
}
