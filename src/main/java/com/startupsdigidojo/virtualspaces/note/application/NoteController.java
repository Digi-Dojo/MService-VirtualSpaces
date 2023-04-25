package com.startupsdigidojo.virtualspaces.note.application;

import com.startupsdigidojo.virtualspaces.note.domain.ManageNotes;
import com.startupsdigidojo.virtualspaces.note.domain.Note;
import com.startupsdigidojo.virtualspaces.note.domain.SearchNotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/v1/notes")

public class NoteController {

    private final ManageNotes manageNotes;
    private final SearchNotes searchNotes;

    @Autowired
    public NoteController(ManageNotes manageNotes, SearchNotes searchNotes){
        this.manageNotes=manageNotes;
        this.searchNotes = searchNotes;
    }

    @GetMapping("/{id}")
    public Note findById(@PathVariable("id")Long id){return manageNotes.readNote(id);}

    @PostMapping("/create")
    public Note createNewNote(@RequestBody CreateNoteDTO dto){
        return manageNotes.createNote(dto.getText(), dto.getPlaceId(), dto.getDate(), dto.getStatusAdded());
    }
    @PostMapping("/update")
    public Note updateNote(@RequestBody UpdateNoteDTO dto){
        return manageNotes.updateNote(dto.getId(),dto.getText(),dto.getPlaceId(), dto.getDate(), dto.getStatusAdded());
    }

    @DeleteMapping("/delete/{id}")
    public Note deleteNote(@PathVariable("id")Long id) {
        return manageNotes.deleteNote(id);
    };

    @GetMapping("/getAll")
    public List<Note> findAll(){
        return searchNotes.findAll();
    }

}
