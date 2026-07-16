package org.wms.notificationservice.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Mirrors com.seyha.taskflow.event.TaskEvent published by task-service.
 * Field shape must stay in sync with the producer.
 */
public record TaskEvent(
        UUID taskId,
        String title,
        String assigneeEmail,
        String actorEmail,
        String eventType,      // CREATED, ASSIGNED, COMPLETED
        LocalDateTime timestamp
) implements Serializable {}
