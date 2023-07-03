package com.startupsdigidojo.virtualspaces.place.application.kafka;


import com.startupsdigidojo.virtualspaces.place.application.dto.StartupEventDTO;
import com.startupsdigidojo.virtualspaces.place.application.dto.UserEventDTO;
import com.startupsdigidojo.virtualspaces.place.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PlaceConsumer {

    @Autowired
    private ManagePlaces managePlaces;

    @KafkaListener(
            containerFactory = "noteCreatedEventKafkaListenerContainerFactory",
            topics = "${com.startupsdigidojo.activitylog.noteEvents.application.kafka.NoteConsumer.topics.startup.created}",
            groupId = "${com.startupsdigidojo.activitylog.userEvents.application.kafka.consumer.group_id}"
    )
    public void syncStartupCreated(StartupEventDTO startupCreatedEvent){
        System.out.println(startupCreatedEvent);
        managePlaces.createPlace("MEETING_ROOM", startupCreatedEvent.getPayload().getId());
        managePlaces.createPlace("BOARD", startupCreatedEvent.getPayload().getId());
    }

    @KafkaListener(
            containerFactory = "noteCreatedEventKafkaListenerContainerFactory",
            topics = "${com.startupsdigidojo.activitylog.noteEvents.application.kafka.NoteConsumer.topics.user.created}",
            groupId = "${com.startupsdigidojo.activitylog.userEvents.application.kafka.consumer.group_id}"
    )
    public void syncUserCreated(UserEventDTO userCreatedEvent){
        System.out.println(userCreatedEvent);
        Place personalDesk = managePlaces.createPlace("PERSONAL_DESK", -1L);
        managePlaces.addUser(personalDesk.getId(), userCreatedEvent.getPayload().getId());
    }
}