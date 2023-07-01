package com.startupsdigidojo.virtualspaces.place.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findById(Long id);
    List<Place> findByType(PlaceTypes type);

}
