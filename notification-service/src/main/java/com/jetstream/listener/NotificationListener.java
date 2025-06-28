package com.jetstream.listener;

import com.jetstream.config.RabbitMQConfig;
import com.jetstream.event.CourseCreatedEvent;
import com.jetstream.event.UserEnrolledEvent;
import com.jetstream.event.UserRegisteredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);

    @RabbitListener(queues = RabbitMQConfig.LMS_QUEUE_NAME)
    public void handleLmsRegisteredEvent(Object event) {
        log.info("==> Received All LMS Event: {}", event);
        log.info("Notification processed successfully for LMS: {}", event);
    }

    @RabbitListener(queues = RabbitMQConfig.ECS_QUEUE_NAME)
    public void handleEcsCreatedEvent(Object event) {
        log.info("==> Received All ECS Event: {}", event);
        log.info("Notification processed successfully for ECS: {}", event);
    }

    @RabbitListener(queues = RabbitMQConfig.CMS_QUEUE_NAME)
    public void handleCmsEnrolledEvent(Object event) {
        log.info("==> Received All CMS Event: {}", event);
        log.info("Notification processed successfully for CMS: {}", event);
    }
}