package com.startupsdigidojo.virtualspaces.note.application.event;

import com.startupsdigidojo.virtualspaces.note.domain.Note;
import lombok.Getter;

public class NoteUpdated extends NoteEvent {

    public static final String NOTE_UPDATED = "Note Updated";

    @Getter
    private String type = NOTE_UPDATED;

    @Getter
    private Note payload;

    public NoteUpdated(Note note) {
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
