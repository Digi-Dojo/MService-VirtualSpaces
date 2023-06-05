package com.startupsdigidojo.virtualspaces.place.application.kafka;


//@RequiredArgsConstructor
//@Component
public class PlaceConsumer {
/* tenere
    @Autowired
    private PlaceRepository placeRepository;

    //@Autowired
    //private PostSyncService postSyncService;

    @KafkaListener(
            topics = "${it.unibz.archlab.digidojo.engagement.kafka.consumer.topics.messages}",
            groupId = "${it.unibz.archlab.digidojo.engagement.kafka.consumer.group_id}"
    )
    public void consume(String jsonMessage) {
        System.out.println("Received:" + jsonMessage);
    }
*/


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