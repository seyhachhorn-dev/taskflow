package org.wms.notificationservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.wms.notificationservice.config.RabbitConfig;
import org.wms.notificationservice.event.TaskEvent;

@Component
@Slf4j
public class TaskEventListener {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void handle(TaskEvent event) {
        switch (event.eventType()) {
            case "CREATED" -> log.info("[NOTIFY] Task '{}' created by {}", event.title(), event.actorEmail());
            case "ASSIGNED" -> log.info("[NOTIFY] Task '{}' assigned to {}", event.title(), event.assigneeEmail());
            case "COMPLETED" -> log.info("[NOTIFY] Task '{}' completed", event.title());
            default -> log.warn("Unknown task event type '{}' for task '{}'", event.eventType(), event.title());
        }
    }
}
