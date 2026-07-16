package com.seyha.taskflow.event;

import com.seyha.taskflow.core.config.RabbitConfig;
import com.seyha.taskflow.domain.Task;
import com.seyha.taskflow.domain.User;
import com.seyha.taskflow.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publish(String routingKey, Task task, String eventType){
        String actor = SecurityUtils.findCurrentUser()
                .map(User::getEmail)
                .orElse("system");

        TaskEvent event = new TaskEvent(
                task.getId(),
                task.getTitle(),
                task.getAssignee() != null ? task.getAssignee().getEmail() : null,
                actor,
                eventType,
                LocalDateTime.now());

        try {
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,routingKey,event);
            log.info(" Published {} for task '{}' by {}", eventType, task.getTitle(), actor);

        }catch (Exception e){
            log.error("Failed to publish {} event: {}", eventType, e.getMessage());

        }

    }
}
