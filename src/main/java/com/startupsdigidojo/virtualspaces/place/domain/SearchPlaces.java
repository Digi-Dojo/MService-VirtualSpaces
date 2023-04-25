package com.startupsdigidojo.virtualspaces.place.domain;

import com.startupsdigidojo.virtualspaces.note.domain.Note;
import com.startupsdigidojo.virtualspaces.note.domain.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchPlaces {

    private final PlaceRepository placeRepository;

    @Autowired
    public SearchPlaces(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place findById(Long id) {
        Optional<Place> searchedPlace = placeRepository.findById(id);

        if(searchedPlace.isEmpty()){
            throw new IllegalArgumentException("Place with id " + id + " is not present in the database");
        }
        return searchedPlace.get();
    }

    public List<Place> findAll(){
        List<Place> list = placeRepository.findAll();
        if(list.isEmpty()){
            throw new IllegalArgumentException("No places in database");
        }
        return list;
    }
}
