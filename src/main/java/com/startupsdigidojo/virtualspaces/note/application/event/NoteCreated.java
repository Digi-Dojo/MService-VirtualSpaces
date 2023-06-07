package com.startupsdigidojo.virtualspaces.note.application.event;

import com.startupsdigidojo.virtualspaces.note.domain.Note;
import lombok.Getter;

public class NoteCreated extends NoteEvent {

    public static final String NOTE_ADDED = "Note added";

    @Getter
    private String type = NOTE_ADDED;

    @Getter
    private Note payload;

    public NoteCreated(Note note) {
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
