package com.startupsdigidojo.virtualspaces.note.application.kafka;
//import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.startupsdigidojo.virtualspaces.note.application.event.NoteCreated;
import com.startupsdigidojo.virtualspaces.note.application.event.NoteDeleted;
import com.startupsdigidojo.virtualspaces.note.application.event.NoteUpdated;
import com.startupsdigidojo.virtualspaces.note.domain.NoteBroadcaster;
import com.startupsdigidojo.virtualspaces.note.domain.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NoteProducer implements NoteBroadcaster {

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void emitNoteAdded(Note note) {
        NoteCreated noteAddedEvent = new NoteCreated(note);
        kafkaTemplate.send("note.created", noteAddedEvent.toJson());
    }

    @Override
    public void emitNoteUpdated(Note note) {
        NoteUpdated noteUpdatedEvent = new NoteUpdated(note);
        kafkaTemplate.send("note.updated", noteUpdatedEvent.toJson());
    }

    @Override
    public void emitNoteDeleted(Note note) {
        NoteDeleted noteRemovedEvent = new NoteDeleted(note);
        kafkaTemplate.send("note.deleted", noteRemovedEvent.toJson());
    }

}
