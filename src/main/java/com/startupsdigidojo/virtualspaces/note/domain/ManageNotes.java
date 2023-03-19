package com.startupsdigidojo.virtualspaces.note.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageNotes {

    private NoteRepository noteRepository;

    @Autowired
    public ManageNotes(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

}
