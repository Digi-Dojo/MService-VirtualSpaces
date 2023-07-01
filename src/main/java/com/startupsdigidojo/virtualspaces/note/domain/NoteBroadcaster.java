package com.startupsdigidojo.virtualspaces.note.domain;

public interface NoteBroadcaster {
    void emitNoteAdded(Note note);
    void emitNoteUpdated(Note note);
    void emitNoteDeleted(Note note);

}
