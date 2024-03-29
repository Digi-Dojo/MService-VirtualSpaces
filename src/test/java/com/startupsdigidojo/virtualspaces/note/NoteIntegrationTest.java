package com.startupsdigidojo.virtualspaces.note;

import com.startupsdigidojo.virtualspaces.note.domain.Note;
import com.startupsdigidojo.virtualspaces.note.domain.NoteRepository;
import com.startupsdigidojo.virtualspaces.place.domain.Place;
import com.startupsdigidojo.virtualspaces.place.domain.PlaceTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteIntegrationTest {

    @LocalServerPort
    private int port;


    private String baseUrl = "http://localhost";
    private String placesUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private NoteRepository noteRepository;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();


    }

    @BeforeEach
    public void setup() {
        baseUrl = baseUrl + ":" + port + "/v1/notes";
        placesUrl = placesUrl + ":" + port + "/v1/places";

        ensureEmptyDatabase();

    }

    @Test
    public void itCreatesANewNote() {

        PlaceTypes typePlace = PlaceTypes.MEETING_ROOM;
        Long startupId = 110L;
        Place inputPlace = new Place(typePlace, startupId);

        // when
        Place place = restTemplate.postForObject(placesUrl + "/create", inputPlace, Place.class);

        String text = "1";
        Long placeId = place.getId();

        boolean status = true;
        Date date = Calendar.getInstance().getTime();

        Note inputNote = new Note(text, placeId, date, status);

        Note response = restTemplate.postForObject(baseUrl + "/create", inputNote, Note.class);

        assertThat(inputNote.getId()).isNull();
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(Note.class);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getPlaceId()).isEqualTo(placeId);
        assertThat(response.getText()).isEqualTo(text);
        assertThat(response.getStatusAdded()).isEqualTo(status);


    }

    @Test
    public void itFindsANoteAfterCreation() {
        PlaceTypes typePlace = PlaceTypes.MEETING_ROOM;
        Long startupId = 110L;
        Place inputPlace = new Place(typePlace, startupId);

        // when
        Place place = restTemplate.postForObject(placesUrl + "/create", inputPlace, Place.class);


        String text = "1";
        Long placeId = place.getId();
        Date date = Calendar.getInstance().getTime();
        boolean status = true;

        Note inputNote = new Note(text, placeId, date, status);

        Note created = restTemplate.postForObject(baseUrl + "/create", inputNote, Note.class);
        Note response = restTemplate.getForObject(baseUrl + "/" + created.getId(), Note.class);

        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(Note.class);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getPlaceId()).isEqualTo(placeId);
        assertThat(response.getText()).isEqualTo(text);
        assertThat(response.getStatusAdded()).isEqualTo(status);
    }

    @Test
    public void itUpdatesANoteAfterCreation() {
        PlaceTypes typePlace = PlaceTypes.MEETING_ROOM;
        Long startupId = 110L;
        Place inputPlace = new Place(typePlace, startupId);

        // when
        Place place = restTemplate.postForObject(placesUrl + "/create", inputPlace, Place.class);


        String text = "1";
        Long placeId = place.getId();
        Date date = Calendar.getInstance().getTime();
        boolean status = true;

        Note inputNote = new Note(text, placeId, date, status);

        Note created = restTemplate.postForObject(baseUrl + "/create", inputNote, Note.class);
        inputNote.setText("2");

        Note updated = restTemplate.postForObject(baseUrl + "/update/" + created.getId(), inputNote, Note.class);

        assertThat(updated).isNotNull();
        assertThat(updated).isInstanceOf(Note.class);
        assertThat(updated.getId()).isNotNull();
        assertThat(updated.getPlaceId()).isEqualTo(placeId);
        assertThat(updated.getText()).isEqualTo(inputNote.getText());
        assertThat(updated.getStatusAdded()).isEqualTo(status);
    }

    @Test
    public void itDeletesANoteAfterCreation() {

        PlaceTypes typePlace = PlaceTypes.MEETING_ROOM;
        Long startupId = 110L;
        Place inputPlace = new Place(typePlace, startupId);

        // when
        Place place = restTemplate.postForObject(placesUrl + "/create", inputPlace, Place.class);

        String text = "1";
        Long placeId = place.getId();

        Date date = Calendar.getInstance().getTime();

        boolean status = true;

        Note inputNote = new Note(text, placeId, date, status);


        Note created = restTemplate.postForObject(baseUrl + "/create", inputNote, Note.class);

        restTemplate.delete(baseUrl + "/delete/" + created.getId());

        assertThatThrownBy(() -> restTemplate.getForObject(baseUrl + "/" + inputNote.getId(), Note.class));
    }

    @Test
    public void itChangesTheStatusOfANoteAfterCreation() {
        PlaceTypes typePlace = PlaceTypes.MEETING_ROOM;
        Long startupId = 110L;
        Place inputPlace = new Place(typePlace, startupId);

        // when
        Place place = restTemplate.postForObject(placesUrl + "/create", inputPlace, Place.class);


        String text = "1";
        Long placeId = place.getId();
        Date date = Calendar.getInstance().getTime();
        boolean status = true;

        Note inputNote = new Note(text, placeId, date, status);

        Note created = restTemplate.postForObject(baseUrl + "/create", inputNote, Note.class);

        inputNote.setStatusAdded(false);

        Note changed = restTemplate.getForObject(baseUrl + "/invert/" + created.getId(), Note.class);

        assertThat(changed).isNotNull();
        assertThat(changed).isInstanceOf(Note.class);
        assertThat(changed.getId()).isNotNull();
        assertThat(changed.getPlaceId()).isEqualTo(placeId);
        assertThat(changed.getText()).isEqualTo(text);
        assertThat(changed.getStatusAdded()).isEqualTo(inputNote.getStatusAdded());
    }

    @Test
    public void itCreatesSeveralNotesAndGetThemAll() {
        PlaceTypes typePlace = PlaceTypes.MEETING_ROOM;
        Long startupId = 110L;
        Place inputPlace = new Place(typePlace, startupId);

        // when
        Place place = restTemplate.postForObject(placesUrl + "/create", inputPlace, Place.class);

        Note inputNote1 = new Note("1", place.getId(), Calendar.getInstance().getTime(), true);
        Note inputNote2 = new Note("2", place.getId(), Calendar.getInstance().getTime(), true);
        Note inputNote3 = new Note("3", place.getId(), Calendar.getInstance().getTime(), true);

        Note created1 = restTemplate.postForObject(baseUrl + "/create", inputNote1, Note.class);
        Note created2 = restTemplate.postForObject(baseUrl + "/create", inputNote2, Note.class);
        Note created3 = restTemplate.postForObject(baseUrl + "/create", inputNote3, Note.class);


        ParameterizedTypeReference<List<Note>> typeRef = new ParameterizedTypeReference<List<Note>>() {
        };

        ResponseEntity<List<Note>> responseEntity = restTemplate.exchange(baseUrl + "/getAll", HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), typeRef);
        List<Note> myModelClasses = responseEntity.getBody();

        assertThat(myModelClasses).isNotNull();

        Note note1 = (Note) myModelClasses.get(0);
        Note note2 = (Note) myModelClasses.get(1);
        Note note3 = (Note) myModelClasses.get(2);

        assertThat(note1.getId()).isNotNull();
        assertThat(note2.getId()).isNotNull();
        assertThat(note3.getId()).isNotNull();
        assertThat(note1.getText()).isEqualTo("1");
        assertThat(note2.getText()).isEqualTo("2");
        assertThat(note3.getText()).isEqualTo("3");


    }


    private void ensureEmptyDatabase() {
        noteRepository.deleteAll();
    }


}
