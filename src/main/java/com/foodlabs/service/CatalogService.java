package com.foodlabs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogService {

    @Value("${topic.name.producer}")
    private String topicNameProducer;

    @Value("${topic.name.consumer}")
    private String topicNameConsumer;

    private final KafkaTemplate<String, String> kafkaTemplate;


    public void send(String payload) {

        log.info("CatalogService::send Payload enviado: {}", payload);
        kafkaTemplate.send(topicNameProducer, payload);
    }

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){
        log.info("Order: {}", payload.value());

    }
}
