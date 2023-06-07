package com.startupsdigidojo.virtualspaces.note.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchNotes {

    private final NoteRepository noteRepository;

    @Autowired
    public SearchNotes(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note findById(Long id) {
        Optional<Note> searchedNote = noteRepository.findById(id);

        if(searchedNote.isEmpty()){
            throw new IllegalArgumentException("Note with id " + id + " is not present in the database");
        }
        return searchedNote.get();
    }

    public List<Note> findAll(){
        List<Note> list = noteRepository.findAll();
        if(list.isEmpty()){
            throw new IllegalArgumentException("No notes in database");
        }
        return list;
    }

}
