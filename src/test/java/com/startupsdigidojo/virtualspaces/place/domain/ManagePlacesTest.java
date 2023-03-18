package com.startupsdigidojo.virtualspaces.place.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ManagePlacesTest {

    private ManagePlaces underTest;

    @Mock
    private PlaceRepository placeRepository;

    @BeforeEach
    void setUp() {
        underTest = new ManagePlaces(placeRepository);
    }


    @Test
    public void itCreatesAPlace() {
        // given
        Place place = new Place(PlaceTypes.PERSONAL_DESK, 000L);
        when(placeRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        when(placeRepository.save(any()))
                .thenReturn(new Place(randomPositiveLong(), place.getType(), place.getStartupId()));

        // when
        Place result = underTest.createPlace("Personal Desk", 000L);

        // then
        assertThat(result).isInstanceOf(Place.class);
        assertThat(result.getType()).isEqualTo(PlaceTypes.PERSONAL_DESK);
        assertThat(result.getStartupId()).isEqualTo(000L);
        assertThat(result.getId())
                .isNotNull()
                .isGreaterThan(0);
    }

    private Long randomPositiveLong() {
        long leftLimit = 1L;
        long rightLimit = 1000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }



}
