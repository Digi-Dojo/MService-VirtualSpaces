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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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


    private void ensureEmptyDatabase() {
        placeRepository.deleteAll();
    }


}
