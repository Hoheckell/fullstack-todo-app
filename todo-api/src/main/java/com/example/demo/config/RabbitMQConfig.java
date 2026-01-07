package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "todo.exchange";
    public static final String QUEUE_NAME = "todo.notifications";
    public static final String ROUTING_KEY = "todo.event";

    @SuppressWarnings("removal")
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange todoExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue todoQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding binding(Queue todoQueue, TopicExchange todoExchange) {
        return BindingBuilder.bind(todoQueue).to(todoExchange).with(ROUTING_KEY);
    }
}