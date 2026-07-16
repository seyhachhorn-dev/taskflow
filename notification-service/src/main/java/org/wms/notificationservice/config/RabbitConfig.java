package org.wms.notificationservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJavaTypeMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Consumes task-service's "task.events" topic exchange.
 * Exchange/routing-key names here must match com.seyha.taskflow.core.config.RabbitConfig in task-service.
 */
@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "task.events";
    public static final String QUEUE = "notification.task-events";
    public static final String ROUTING_KEY_PATTERN = "task.*";

    @Bean
    public TopicExchange taskExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue taskEventsQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public Binding taskEventsBinding(Queue taskEventsQueue, TopicExchange taskExchange) {
        return BindingBuilder.bind(taskEventsQueue).to(taskExchange).with(ROUTING_KEY_PATTERN);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter();
        // Ignore the producer's __TypeId__ header (it points at task-service's own class)
        // and always deserialize into the @RabbitListener method's declared parameter type.
        converter.setTypePrecedence(JacksonJavaTypeMapper.TypePrecedence.INFERRED);
        return converter;
    }
}
