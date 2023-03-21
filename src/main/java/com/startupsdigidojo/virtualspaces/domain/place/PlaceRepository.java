package com.startupsdigidojo.virtualspaces.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findById(Long id);

}
