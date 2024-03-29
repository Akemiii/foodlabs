package com.foodlabs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogService {

    @Value("${topic.name.producer}")
    private String topicNameProducer;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String payload) {

        log.info("CatalogService::send Payload enviado: {}", payload);
        kafkaTemplate.send(topicNameProducer, payload);
    }
}
