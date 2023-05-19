package com.startupsdigidojo.virtualspaces.note.domain;

public interface Broadcaster {
    void emitNoteAdded(Note note);
}
