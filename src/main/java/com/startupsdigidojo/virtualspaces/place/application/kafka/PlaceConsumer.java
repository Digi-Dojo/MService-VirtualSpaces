package com.startupsdigidojo.virtualspaces.place.application.kafka;


import com.startupsdigidojo.virtualspaces.place.domain.Place;
import com.startupsdigidojo.virtualspaces.place.domain.PlaceBroadcaster;
import com.startupsdigidojo.virtualspaces.place.domain.PlaceRepository;
import com.startupsdigidojo.virtualspaces.place.domain.PlaceSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

//@RequiredArgsConstructor
//@Component
public class PlaceConsumer {


    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceSyncService placeSyncService;

    @KafkaListener(
            topics = "${it.unibz.archlab.digidojo.engagement.kafka.consumer.topics.messages}",
            groupId = "${it.unibz.archlab.digidojo.engagement.kafka.consumer.group_id}"
    )
    public void consume(String jsonMessage) {
        System.out.println("Received:" + jsonMessage);
    }



    /* modificare
    @KafkaListener(
            containerFactory = "postWrittenEventKafkaListenerContainerFactory",
            topics = "${it.unibz.archlab.digidojo.engagement.kafka.consumer.topics.posts_written}",
            groupId = "${it.unibz.archlab.digidojo.engagement.kafka.consumer.group_id}"
    )

    public void syncPostWritten(PostWrittenEvent postWrittenEvent) {
        PostWrittenEvent.Payload payload = postWrittenEvent.getPayload();

        postSyncService.storePost(payload.getUuid(), payload.getSlug());
    }
    */

    /*
    @KafkaListener(
            containerFactory = "postErasedEventKafkaListenerContainerFactory",
            topics = "${it.unibz.archlab.digidojo.engagement.kafka.consumer.topics.posts_erased}",
            groupId = "${it.unibz.archlab.digidojo.engagement.kafka.consumer.group_id}"
    )
    public void syncPostErased(PostErasedEvent postErasedEvent) {
        PostErasedEvent.Payload payload = postErasedEvent.getPayload();

        postSyncService.deletePost(payload.getUuid());
    }
    */
}