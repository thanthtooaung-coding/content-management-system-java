package com.jetstream.learningmanagementservice.event;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class EventPublisherService {
    private static final Logger log = LoggerFactory.getLogger(EventPublisherService.class);
    private final RabbitTemplate rabbitTemplate;

    public <T> void publishLmsEvent(T payload, String eventType, String routingKey) {
        LmsEvent<T> event = new LmsEvent<>(eventType, payload, Instant.now());
        log.info("Publishing {} to exchange 'lms-exchange' with key '{}'", eventType, routingKey);
        rabbitTemplate.convertAndSend("lms-exchange", routingKey, event);
    }
}