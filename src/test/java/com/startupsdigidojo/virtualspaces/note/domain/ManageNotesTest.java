package com.startupsdigidojo.virtualspaces.note.domain;

import com.startupsdigidojo.virtualspaces.place.domain.ManagePlaces;
import com.startupsdigidojo.virtualspaces.place.domain.Place;
import com.startupsdigidojo.virtualspaces.place.domain.PlaceTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ManageNotesTest {
    private ManageNotes underTest;

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private ManagePlaces managePlaces;

    @BeforeEach
    void setup() {
        underTest = new ManageNotes(noteRepository, managePlaces);
    }

    @Test
    public void itCreatesANote() {

    }

    @Test
    public void itSticksTheNote() {

    }
/*
    // sbagliato
    @Test
    public void itThrowsExceptionForNonValidNoteType() {
        // when
        long wrongPlaceId = 00L;

        // then
        assertThatThrownBy(() -> underTest.createNote("", true, wrongPlaceId, "20042023s"))
                .isInstanceOf(IllegalArgumentException.class);
    }
*/
    @Test
    public void itFindsNoteById() {
        // given
        Date date = Calendar.getInstance().getTime();
        Note note = new Note("", 125L, date, true);
        when(noteRepository.findById(1L))
                .thenReturn(Optional.of(new Note(1L, note.getText(), note.getPlaceId(), note.getDate(), note.getStatusAdded())));

        // when // Long id, String text, Long placeId, Date date, boolean statusAdded
        Note result = underTest.readNote(1L);

        // then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getText()).isEqualTo("");
        assertThat(result.getPlaceId()).isEqualTo(125L);
        assertThat(result.getDate()).isEqualTo(date);
        assertThat(result.getStatusAdded()).isEqualTo(true);
    }

    @Test
    public void itThrowsExceptionIfPlaceIdDontExist() {
        // then
        assertThatThrownBy(() -> underTest.readNote(1L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void itUpdatesANote() {
        Date date = Calendar.getInstance().getTime();
        Note note = new Note("", 125L, date, true);
        when(noteRepository.findById(1L))
                .thenReturn(Optional.of(new Note(1L, note.getText(), note.getPlaceId(), note.getDate(), note.getStatusAdded())));

        Note noteChange = new Note("update", 100L, date, true);
        when(noteRepository.save(any()))
                .thenReturn(Optional.of(new Note(1L, note.getText(), note.getPlaceId(), note.getDate(), note.getStatusAdded())));

        Note result = underTest.createNote("", true, 125L, date.toString());
        // 1L, note.getText(), note.getPlaceId(), note.getDate(), note.getStatusAdded())));
        Note resultModified = underTest.updateNote(1L,"", false, 100L, date.toString());

    }
}
