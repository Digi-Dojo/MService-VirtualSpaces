package com.startupsdigidojo.virtualspaces.note.application.event;

import com.startupsdigidojo.virtualspaces.note.domain.Note;
import lombok.Getter;

public class NoteDeleted extends NoteEvent {

    public static final String NOTE_REMOVED = "Note Removed";

    @Getter
    private String type = NOTE_REMOVED;

    @Getter
    private Note payload;

    public NoteDeleted(Note note) {
        payload = note;
    }

    public String toJson() {
        return "{" +
                "\"type\": \"" + type + "\"," +
                "\"payload\": {" +
                "\"uuid\": \"" + payload.getId() + "\"," +
                "\"text\": \"" + payload.getText() + "\"," +
                "\"place\": \"" + payload.getPlaceId() + "\"," +
                "\"date\": \"" + payload.getDate() + "\"," +
                "\"statusAdded\": \"" + payload.getStatusAdded() + "\"" +
                "\"time\": \"" + System.currentTimeMillis() + "\"" +
                "}" +
                "}";

    }
}
