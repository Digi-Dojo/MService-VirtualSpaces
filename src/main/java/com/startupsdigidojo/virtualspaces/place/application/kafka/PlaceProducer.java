package com.startupsdigidojo.virtualspaces.place.application.kafka;

import com.startupsdigidojo.virtualspaces.place.application.event.*;
import com.startupsdigidojo.virtualspaces.place.domain.Place;
import com.startupsdigidojo.virtualspaces.place.domain.PlaceBroadcaster;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PlaceProducer implements PlaceBroadcaster {

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void emitPlaceAdded(Place place) {
        PlaceCreated placeCreated = new PlaceCreated(place);
        kafkaTemplate.send("place.created", placeCreated.toJson());
    }

    @Override
    public void emitPlaceUpdated(Place place) {
        PlaceUpdated placeUpdated = new PlaceUpdated(place);
        kafkaTemplate.send("place.updated", placeUpdated.toJson());

    }

    @Override
    public void emitPlaceDeleted(Place place) {
        PlaceDeleted placeDeleted = new PlaceDeleted(place);
        kafkaTemplate.send("place.deleted", placeDeleted.toJson());


    }

    @Override
    public void emitUserEnteredPlace(Place place, Long userId) { //add User
        UserEnteredPlace userEnteredPlace = new UserEnteredPlace(place, userId);
        kafkaTemplate.send("place.user_entered", userEnteredPlace.toJson());
    }

    @Override
    public void emitUserLeftPlace(Place place, Long userId) { //add User
        UserLeftPlace userLeftPlace = new UserLeftPlace(place, userId);
        kafkaTemplate.send("place.user_left", userLeftPlace.toJson());
    }


}
