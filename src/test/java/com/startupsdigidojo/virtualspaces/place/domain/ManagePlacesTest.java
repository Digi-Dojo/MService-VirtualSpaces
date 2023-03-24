package com.startupsdigidojo.virtualspaces.place.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    public void itThrowsExceptionForNonValidPlaceType() {
        // when
        String wrongPlaceType = "Random Inexisting Place Type";

        // then
        assertThatThrownBy(() -> underTest.createPlace(wrongPlaceType, 000L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void itFindsPlaceById() {
        // given
        Place place = new Place(PlaceTypes.PERSONAL_DESK, 125L);
        when(placeRepository.findById(1L))
                .thenReturn(Optional.of(new Place(1L, place.getType(), place.getStartupId())));

        // when
        Place result = underTest.readPlace(1L);

        // then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getType()).isEqualTo(PlaceTypes.PERSONAL_DESK);
        assertThat(result.getStartupId()).isEqualTo(125L);
    }

    @Test
    public void itThrowsExceptionIfPlaceIdDontExist() {
        // then
        assertThatThrownBy(() -> underTest.readPlace(1L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void itUpdatedAPlace() {
        // given
        Place place = new Place(PlaceTypes.PERSONAL_DESK, 125L);
        when(placeRepository.findById(1L))
                .thenReturn(Optional.of(new Place(1L, place.getType(), place.getStartupId())));

        Place placeChange = new Place(PlaceTypes.MEETING_ROOM, 100L);
        when(placeRepository.save(any()))
                .thenReturn(new Place(1L, placeChange.getType(), placeChange.getStartupId()));

        // when
        Place result = underTest.createPlace("Personal Desk", 125L);
        Place resultModified = underTest.updatePlace(1L, "Meeting Room", 100L);

        // then
        assertThat(resultModified.getId()).isEqualTo(1L);
        assertThat(resultModified.getType()).isEqualTo(PlaceTypes.MEETING_ROOM);
        assertThat(result.getStartupId()).isEqualTo(100L);
    }

    @Test
    public void itThrowsErrorUpdatingNonExistingPlace() {
        // given
        Place place = new Place(PlaceTypes.PERSONAL_DESK, 125L);
        when(placeRepository.findById(1L))
                .thenReturn(Optional.of(new Place(1L, place.getType(), place.getStartupId())));

        Place placeChange = new Place(PlaceTypes.MEETING_ROOM, 100L);
        when(placeRepository.save(any()))
                .thenReturn(new Place(1L, placeChange.getType(), placeChange.getStartupId()));

        // when
        Place result = underTest.createPlace("Personal Desk", 125L);

        // then
        assertThatThrownBy(() -> underTest.updatePlace(300L, "Meeting Room", 100L));
    }

    @Test
    public void itThrowsErrorUpdatingAPlaceWithNonAcceptedTypePlace() {
        // given
        Place place = new Place(PlaceTypes.PERSONAL_DESK, 125L);
        when(placeRepository.findById(1L))
                .thenReturn(Optional.of(new Place(1L, place.getType(), place.getStartupId())));

        Place placeChange = new Place(PlaceTypes.MEETING_ROOM, 100L);
        when(placeRepository.save(any()))
                .thenReturn(new Place(1L, placeChange.getType(), placeChange.getStartupId()));

        // when
        Place result = underTest.createPlace("Personal Desk", 125L);

        // then
        assertThatThrownBy(() -> underTest.updatePlace(1L, "Random Non Existing Place Type", 100L));
    }

    private Long randomPositiveLong() {
        long leftLimit = 1L;
        long rightLimit = 1000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }



}
