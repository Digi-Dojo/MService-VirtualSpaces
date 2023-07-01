package com.startupsdigidojo.virtualspaces.note.application;

import com.startupsdigidojo.virtualspaces.note.domain.ManageNotes;
import com.startupsdigidojo.virtualspaces.note.domain.Note;
import com.startupsdigidojo.virtualspaces.note.domain.SearchNotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping("/invert/{id}")
    public Note invertStatus(@PathVariable("id")Long id){
        Note note = manageNotes.readNote(id);
        Format f = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = f.format(note.getDate());
        System.out.println(strDate);
        return manageNotes.updateNote(id, note.getText(), note.getPlaceId(), strDate, !note.getStatusAdded());}

    @PostMapping("/create")
    public Note createNewNote(@RequestBody CreateNoteDTO dto){
        return manageNotes.createNote(dto.getText(), dto.getPlaceId(), dto.getDate(), dto.getStatusAdded());
    }
    @PostMapping("/update/{id}")
    public Note updateNote(@PathVariable("id") Long id,@RequestBody UpdateNoteDTO dto){
        return manageNotes.updateNote(id,dto.getText(),dto.getPlaceId(), dto.getDate(), dto.getStatusAdded());
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
