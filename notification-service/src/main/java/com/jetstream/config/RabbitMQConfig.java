package com.jetstream.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // --- Configuration for User Events ---
    public static final String LMS_EXCHANGE = "lms-exchange";
    public static final String LMS_QUEUE_NAME = "lms-notifications-queue";
    public static final String LMS_ROUTING_KEY = "lms.created";

    @Bean
    public TopicExchange lmsExchange() {
        return new TopicExchange(LMS_EXCHANGE);
    }

    @Bean
    public Queue lmsQueue() {
        return new Queue(LMS_QUEUE_NAME, true);
    }

    @Bean
    public Binding lmsBinding(@Qualifier("lmsQueue") Queue queue, @Qualifier("lmsExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(LMS_ROUTING_KEY);
    }

    // --- Configuration for Course Events ---
    public static final String ECS_EXCHANGE_NAME = "ecs-exchange";
    public static final String ECS_QUEUE_NAME = "ecs-notifications-queue";
    public static final String ECS_ROUTING_KEY = "ecs.created";

    @Bean
    public TopicExchange ecsExchange() {
        return new TopicExchange(ECS_EXCHANGE_NAME);
    }

    @Bean
    public Queue ecsQueue() {
        return new Queue(ECS_QUEUE_NAME, true);
    }

    @Bean
    public Binding ecsBinding(@Qualifier("ecsQueue") Queue queue, @Qualifier("ecsExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ECS_ROUTING_KEY);
    }

    // --- Configuration for Enrollment Events ---
    public static final String CMS_EXCHANGE_NAME = "cms-exchange";
    public static final String CMS_QUEUE_NAME = "cms-notifications-queue";
    public static final String CMS_ROUTING_KEY = "cms.created";

    @Bean
    public TopicExchange cmsExchange() {
        return new TopicExchange(CMS_EXCHANGE_NAME);
    }

    @Bean
    public Queue cmsQueue() {
        return new Queue(CMS_QUEUE_NAME, true);
    }

    @Bean
    public Binding cmsBinding(@Qualifier("cmsQueue") Queue queue, @Qualifier("cmsExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(CMS_ROUTING_KEY);
    }


    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}