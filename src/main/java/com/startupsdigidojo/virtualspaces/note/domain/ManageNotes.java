package com.startupsdigidojo.virtualspaces.note.domain;

import com.startupsdigidojo.virtualspaces.note.application.kafka.NoteProducer;
import com.startupsdigidojo.virtualspaces.place.domain.ManagePlaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class ManageNotes {

    private final ManagePlaces managePlaces;
    private final NoteRepository noteRepository;
    private final int TEXT_MIN_LENGTH = 1, TEXT_MAX_LENGTH = 100;

    @Autowired
    private NoteProducer broadcaster;

    @Autowired
    public ManageNotes(NoteRepository noteRepository, ManagePlaces managePlaces) {
        this.noteRepository = noteRepository;
        this.managePlaces = managePlaces;
    }

    private void validateTextNote(String text){
        int textToLong = text.length();

        if(textToLong > TEXT_MAX_LENGTH || textToLong < TEXT_MIN_LENGTH)
            throw new IllegalArgumentException("Text length is not valid; It must be long at least " + TEXT_MIN_LENGTH +
                    "characters and at most " + TEXT_MAX_LENGTH + " characters");
    }

    private void validatePlace(Long placeId){

        managePlaces.readPlace(placeId);
    }

    private Note validateNote(Long id){
        Optional<Note> maybeNote =noteRepository.findById(id);

        if(maybeNote.isEmpty())
            throw new IllegalArgumentException("Place with id '"+ id +"'does not exist yet!");

        return maybeNote.get();
    }

    private Date validateDate(String date){

        Date date1;

        System.out.println(date);

        try {
             date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date " + date + " is not of the format dd/MM/yyyy");
        }

        return date1;

    }


    public Note createNote(String text, Long placeId, String date, boolean status){

        validateTextNote(text);
        validatePlace(placeId);

        Date date1 = validateDate(date);
        Note note = new Note(text,placeId,date1,status);
        broadcaster.emitNoteAdded(note);
        return noteRepository.save(note);
    }

    public Note readNote(Long id){
        Note note = validateNote(id);

        return note;
    }

    public Note updateNote(Long id, String text, Long placeId, String date, boolean status){


        Note note = validateNote(id);
        validateTextNote(text);
        Date date1 = validateDate(date);
        System.out.println("arrivo");
        validatePlace(placeId);

        note.setStatusAdded(status);

        System.out.println(note.getStatusAdded());

        note.setText(text);
        note.setPlaceId(placeId);

        note.setDate(date1);
        broadcaster.emitNoteUpdated(note);

        return noteRepository.save(note);
    }

    public Note deleteNote (long id){

        Note note= validateNote(id);
        broadcaster.emitNoteDeleted(note);
        noteRepository.delete(note);

        return note;
    }

}
