package com.startupsdigidojo.virtualspaces.domain.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class ManageNotes {

    private NoteRepository noteRepository;
    private final int TEXT_MIN_LENGTH = 1, TEXT_MAX_LENGTH = 100;

    @Autowired
    public ManageNotes(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    private void validateTextNote(String text){
        int textToLong = text.length();

        if(textToLong > TEXT_MAX_LENGTH || textToLong < TEXT_MIN_LENGTH)
            throw new IllegalArgumentException("Text length is not valid; It must be long at least " + TEXT_MIN_LENGTH +
                    "characters and at most " + TEXT_MAX_LENGTH + " characters");
    }

//    private Note validatePlace(Long placeId){

      //  ManagePlaces managePlaces = new ManagePlaces(); check place repository;

     //   if(maybePlace.isEmpty())
       //     throw new IllegalArgumentException("Place with id '" + placeId + "'does not exist yet!");

     //   return maybePlace.get();
 //   }

    private Note validateNote(Long id){
        Optional<Note> maybeNote =noteRepository.findById(id);

        if(maybeNote.isEmpty())
            throw new IllegalArgumentException("Place with id '"+ id +"'does not exist yet!");

        return maybeNote.get();
    }

    private Date validateDate(String date){

        Date date1;

        try {
             date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date " + date + " is not of the format dd/MM/yyyy");
        }

        return date1;

    }


    public Note createNote(String text, boolean status, Long placeId,String date){

        validateTextNote(text);
        validateNote(placeId);

        Date date1 = validateDate(date);

        return noteRepository.save(new Note(text,placeId,date1,status));
    }

    public Note readNote(Long id){
        Note note = validateNote(id);

        return note;
    }

    public Note updateNote(Long id,String text, long placeId, boolean status){
        Note note = validateNote(id);
        validateTextNote(text);

        //validatePlace(placeId);

        note.setStatusAdded(status);

        note.setText(text);
        note.setPlaceId(placeId);

        return noteRepository.save(note);
    }

    public Note deleteNote (long id){

        Note note= validateNote(id);

        noteRepository.delete(note);

        return note;
    }

}
