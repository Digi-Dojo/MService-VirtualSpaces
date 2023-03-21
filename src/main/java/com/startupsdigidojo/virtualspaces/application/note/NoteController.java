package com.startupsdigidojo.virtualspaces.application.note;

import com.startupsdigidojo.virtualspaces.domain.note.ManageNotes;
import com.startupsdigidojo.virtualspaces.domain.note.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/v1/notes")

public class NoteController {

    private ManageNotes manageNotes;

    @Autowired
    public NoteController (ManageNotes manageNotes){ this.manageNotes=manageNotes;}

    @GetMapping("{id}")
    public Note findById(@PathVariable("id")Long id){return manageNotes.readNote(id);}

    @PostMapping
    public Note createNewNote(@RequestBody CreateNoteDTO dto){
        return manageNotes.createNote(dto.getText(),dto.getStatusAdded(),dto.getPlaceId(),dto.getDate());
    }
    @PostMapping
    public Note updateNote(@RequestBody UpdateNoteDTO dto){
        return manageNotes.updateNote(dto.getId(),dto.getText(),dto.getPlaceId(),dto.getStatusAdded());
    }

    @DeleteMapping("{id}")
    public Note deleteNote(@PathVariable("id")Long id) {
        return manageNotes.deleteNote(id);
    };


}
