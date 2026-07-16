package com.seyha.taskflow.core.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE     = "task.events";
    public static final String RK_CREATED   = "task.created";
    public static final String RK_ASSIGNED  = "task.assigned";
    public static final String RK_COMPLETED = "task.completed";

    @Bean
    public TopicExchange taskExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        // Switch from Jackson2JsonMessageConverter to JacksonJsonMessageConverter
        return new JacksonJsonMessageConverter();
    }
}
