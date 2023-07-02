/**
 * The `com.startupsdigidojo.virtualspaces.note.application` package contains the controllers and request mappings
 * for managing notes in the virtual spaces application.
 */
package com.startupsdigidojo.virtualspaces.note.application;

import com.startupsdigidojo.virtualspaces.note.domain.ManageNotes;
import com.startupsdigidojo.virtualspaces.note.domain.Note;
import com.startupsdigidojo.virtualspaces.note.domain.SearchNotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * The `NoteController` class handles the HTTP requests for managing notes.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/v1/notes")
public class NoteController {

    private final ManageNotes manageNotes;
    private final SearchNotes searchNotes;

    /**
     * Constructs a new `NoteController` with the specified `ManageNotes` and `SearchNotes` instances.
     *
     * @param manageNotes the instance of `ManageNotes` used for note management
     * @param searchNotes the instance of `SearchNotes` used for note searching
     */
    @Autowired
    public NoteController(ManageNotes manageNotes, SearchNotes searchNotes){
        this.manageNotes = manageNotes;
        this.searchNotes = searchNotes;
    }

    /**
     * Retrieves a note with the specified ID.
     *
     * @param id the ID of the note to retrieve
     * @return the note with the specified ID
     */
    @GetMapping("/{id}")
    public Note findById(@PathVariable("id")Long id){
        return manageNotes.readNote(id);
    }

    /**
     * Inverts the status of a note with the specified ID.
     *
     * @param id the ID of the note to update
     * @return the updated note with the inverted status
     */
    @GetMapping("/invert/{id}")
    public Note invertStatus(@PathVariable("id")Long id){
        Note note = manageNotes.readNote(id);
        Format f = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = f.format(note.getDate());
        System.out.println(strDate);
        return manageNotes.updateNote(id, note.getText(), note.getPlaceId(), strDate, !note.getStatusAdded());
    }

    /**
     * Creates a new note based on the provided data.
     *
     * @param dto the data for creating the note
     * @return the newly created note
     */
    @PostMapping("/create")
    public Note createNewNote(@RequestBody CreateNoteDTO dto){
        return manageNotes.createNote(dto.getText(), dto.getPlaceId(), dto.getDate(), dto.getStatusAdded());
    }

    /**
     * Updates an existing note with the specified ID using the provided data.
     *
     * @param id  the ID of the note to update
     * @param dto the updated data for the note
     * @return the updated note
     */
    @PostMapping("/update/{id}")
    public Note updateNote(@PathVariable("id") Long id, @RequestBody UpdateNoteDTO dto){
        return manageNotes.updateNote(id, dto.getText(), dto.getPlaceId(), dto.getDate(), dto.getStatusAdded());
    }

    /**
     * Deletes a note with the specified ID.
     *
     * @param id the ID of the note to delete
     * @return the deleted note
     */
    @DeleteMapping("/delete/{id}")
    public Note deleteNote(@PathVariable("id")Long id) {
        return manageNotes.deleteNote(id);
    };

    /**
     * Retrieves all notes.
     *
     * @return a list of all notes
     */
    @GetMapping("/getAll")


    public List<Note> findAll(){
        return searchNotes.findAll();
    }

    /**
     * Retrieves notes based on the place ID.
     *
     * @param id the ID of the place to search notes for
     * @return a list of notes belonging to the specified place ID
     */
    @GetMapping("/getFromPlaceId/{id}")
    public List<Note> findByPlace(@PathVariable("id") Long id){
        return searchNotes.findByPlaceId(id);
    }
}