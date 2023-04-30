package com.startupsdigidojo.virtualspaces.note.domain;

import com.startupsdigidojo.virtualspaces.place.domain.ManagePlaces;
import com.startupsdigidojo.virtualspaces.place.domain.Place;
import com.startupsdigidojo.virtualspaces.place.domain.PlaceTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
/*
    @Test
    public void itFindsNoteById() {
        // given
        Date date = Calendar.getInstance().getTime();
        Note note = underTest.createNote("1", 125L, currentDate(), true);
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
*/
    @Test
    public void itThrowsExceptionIfPlaceIdDontExist() {
        // then
        assertThatThrownBy(() -> underTest.readNote(1L))
                .isInstanceOf(IllegalArgumentException.class);
    }
/*
    @Test
    public void itUpdatesANote() {

        Note note = underTest.createNote("1", 125L, currentDate(), true);

        Date date1;

        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse("09/04/2001");
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date " + "09/04/2001" + " is not of the format dd/MM/yyyy");
        }
        Note note2 = new Note("1", 125L, date1, true);
        when(noteRepository.findById(1L))
                .thenReturn(Optional.of(new Note(1L, note.getText(), note.getPlaceId(), note.getDate(), note.getStatusAdded())));

        Note noteChange = underTest.createNote("update", 100L, currentDate(), true);
        when(noteRepository.save(any()))
                .thenReturn(Optional.of(new Note(1L, noteChange.getText(), noteChange.getPlaceId(), noteChange.getDate(), noteChange.getStatusAdded())));

        Note result = underTest.createNote("1",125L, currentDate(), true);
        Note resultModified = underTest.updateNote(1L,"1", 100L, currentDate(), false);

        // then
        assertThat(resultModified.getId()).isEqualTo(1L);
        assertThat(resultModified.getStatusAdded()).isEqualTo(false);
        assertThat(result.getPlaceId()).isEqualTo(100L);
    }
*/
    @Test
    public void itThrowsExceptionIfDescriptionIsInvalid() {
        // must be long at least 1characters and at most 100 characters
        assertThatThrownBy(() -> underTest.createNote("", 125L, currentDate(), true))
                .isInstanceOf(IllegalArgumentException.class);
    }
/*
    @Test
    public void itThrowsExceptionIfDateHasBadFormat() {
        String badFormatDate = Calendar.getInstance().getTime().toString();
        assertThatThrownBy(() -> underTest.createNote("", 125L, badFormatDate, true))
                .isInstanceOf(IllegalArgumentException.class);
    }
*/
/*
    @Test
    public void itThrowsErrorUpdatingNonExistingNote() {
        // given
        Note note = underTest.createNote("1", 125L, currentDate(), true);
        when(noteRepository.findById(1L))
                .thenReturn(Optional.of(new Note(1L, note.getText(), note.getPlaceId(), note.getDate(), note.getStatusAdded())));

        Note noteChange = underTest.createNote("update", 100L, currentDate(), true);
        when(noteRepository.save(any()))
                .thenReturn(Optional.of(new Note(1L, noteChange.getText(), noteChange.getPlaceId(), noteChange.getDate(), noteChange.getStatusAdded())));

        // when
        Note result = underTest.createNote("1", 125L, currentDate(), true);

        // then 
        assertThatThrownBy(() -> underTest.updateNote(300L, "Closing status", 125L, currentDate(), false));
    }
*/
    private String currentDate() {
        Date badFormatDate = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(badFormatDate);
        return date;
    }

}
