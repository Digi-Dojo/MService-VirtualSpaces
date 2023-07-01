package com.startupsdigidojo.virtualspaces.place.domain;

public interface PlaceBroadcaster {
    void emitPlaceAdded(Place place);
    void emitPlaceUpdated(Place place);
    void emitPlaceDeleted(Place place);
    void emitUserEnteredPlace(Place place); //add User
    void emitUserLeftPlace(Place place); //add User

}
