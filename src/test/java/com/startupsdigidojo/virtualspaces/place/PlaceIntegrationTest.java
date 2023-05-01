package com.startupsdigidojo.virtualspaces.place;

import com.startupsdigidojo.virtualspaces.place.domain.Place;
import com.startupsdigidojo.virtualspaces.place.domain.PlaceRepository;
import com.startupsdigidojo.virtualspaces.place.domain.PlaceTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

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

    private void ensureEmptyDatabase() {
        placeRepository.deleteAll();
    }


}
