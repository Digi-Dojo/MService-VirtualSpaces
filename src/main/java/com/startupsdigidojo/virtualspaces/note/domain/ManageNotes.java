package com.startupsdigidojo.virtualspaces.note.domain;

import com.startupsdigidojo.virtualspaces.place.domain.ManagePlaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ManageNotes {

    private NoteRepository noteRepository;

    @Autowired
    public ManageNotes(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    private void validateNote(String text){
        int textToLong = text.length();

        if(textToLong >100 && textToLong < 1)
            throw new IllegalArgumentException("Text is not valid");



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

    }


    public Note createNote(String text, boolean status, Long placeId,String date){

        validateNote(text);
        validateNote(placeId);

        Date date1 = validateDate(date);

        return noteRepository.save(new Note(text,status,placeId,date1));
    }

    public Note readNote(Long id){
        Note note = validateNote(id);

        return note;
    }

    public Note updateNote(Long id,String text, long placeId, boolean status){
        Note note = validateNote(id);
        validateNote(text);

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
