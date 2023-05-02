package com.startupsdigidojo.virtualspaces.place;

import com.startupsdigidojo.virtualspaces.place.domain.Place;
import com.startupsdigidojo.virtualspaces.place.domain.PlaceRepository;
import com.startupsdigidojo.virtualspaces.place.domain.PlaceTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaceIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private PlaceRepository placeRepository;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setup() {
        baseUrl = baseUrl + ":" + port + "/v1/places";

        ensureEmptyDatabase();
    }

    @Test
    public void itCreatesANewPlace() {
        // given
        PlaceTypes typePlace = PlaceTypes.MEETING_ROOM;
        Long startupId = 110L;
        Place inputPlace = new Place(typePlace, startupId);

        // when
        Place response = restTemplate.postForObject(baseUrl + "/create", inputPlace, Place.class);

        // then
        assertThat(inputPlace.getId()).isNull();
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(Place.class);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getType()).isEqualTo(typePlace);
        assertThat(response.getStartupId()).isEqualTo(startupId);
    }

    @Test
    public void itFindsAPlaceAfterCreation() {
        // given
        PlaceTypes typePlace = PlaceTypes.MEETING_ROOM;
        Long startupId = 110L;
        Place inputPlace = new Place(typePlace, startupId);

        // when
        Place created = restTemplate.postForObject(baseUrl + "/create", inputPlace, Place.class);

        Place response = restTemplate.getForObject(baseUrl + "/" + created.getId(), Place.class);

        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(Place.class);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getType()).isEqualTo(typePlace);
        assertThat(response.getStartupId()).isEqualTo(startupId);
    }

    @Test
    public void itUpdatesAPlaceAfterCreation() {
        // given
        PlaceTypes typePlace = PlaceTypes.MEETING_ROOM;
        Long startupId = 110L;
        Place inputPlace = new Place(typePlace, startupId);

        // when
        Place created = restTemplate.postForObject(baseUrl + "/create", inputPlace, Place.class);

        inputPlace.setType(PlaceTypes.PERSONAL_DESK);
        inputPlace.setStartupId(10L);

        Place updated = restTemplate.postForObject((baseUrl + "/update/" + created.getId()), inputPlace ,Place.class);

        assertThat(updated).isNotNull();
        assertThat(updated).isInstanceOf(Place.class);
        assertThat(updated.getId()).isNotNull();
        assertThat(updated.getType()).isEqualTo(inputPlace.getType());
        assertThat(updated.getStartupId()).isEqualTo(inputPlace.getStartupId());
    }

    @Test
    public void itDeletesAPlaceAfterCreation() {
        // given
        PlaceTypes typePlace = PlaceTypes.MEETING_ROOM;
        Long startupId = 110L;
        Place inputPlace = new Place(typePlace, startupId);

        // when
        Place created = restTemplate.postForObject(baseUrl + "/create", inputPlace, Place.class);

        restTemplate.delete(baseUrl + "/delete/" + created.getId());

        //place no longer existing
        assertThatThrownBy(() -> restTemplate.getForObject(baseUrl + "/" + inputPlace.getId(), Place.class));
    }

    @Test
    public void itCreatesSeveralPlacesAndGetThemAll() {
        // given
        Place inputPlace1 = new Place(PlaceTypes.PERSONAL_DESK, 100L);
        Place inputPlace2 = new Place(PlaceTypes.MEETING_ROOM, 100L);
        Place inputPlace3 = new Place(PlaceTypes.BOARD, 200L);

        // when
        restTemplate.postForObject(baseUrl + "/create", inputPlace1, Place.class);
        restTemplate.postForObject(baseUrl + "/create", inputPlace2, Place.class);
        restTemplate.postForObject(baseUrl + "/create", inputPlace3, Place.class);

        ParameterizedTypeReference<List<Place>> typeRef = new ParameterizedTypeReference<List<Place>>() {
        };

        ResponseEntity<List<Place>> responseEntity = restTemplate.exchange(baseUrl + "/getAll", HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), typeRef);
        List<Place> myModelClasses = responseEntity.getBody();

        // then
        assertThat(myModelClasses).isNotNull();

        System.out.println(myModelClasses.get(0));

        Place place1 = (Place) myModelClasses.get(0);
        Place place2 = (Place) myModelClasses.get(1);
        Place place3 = (Place) myModelClasses.get(2);

        assertThat(place1.getId()).isNotNull();
        assertThat(place1.getType()).isEqualTo(PlaceTypes.PERSONAL_DESK);
        assertThat(place1.getStartupId()).isEqualTo(100L);
        assertThat(place2.getId()).isNotNull();
        assertThat(place2.getType()).isEqualTo(PlaceTypes.MEETING_ROOM);
        assertThat(place2.getStartupId()).isEqualTo(100L);
        assertThat(place3.getId()).isNotNull();
        assertThat(place3.getType()).isEqualTo(PlaceTypes.BOARD);
        assertThat(place3.getStartupId()).isEqualTo(200L);
    }

    @Test
    public void itThrowsErrorSearchingNonExistingPlaces() {
        //db is empty
        assertThatThrownBy(() -> restTemplate.getForObject(baseUrl + "/50", Place.class));
    }


    private void ensureEmptyDatabase() {
        placeRepository.deleteAll();
    }


}
