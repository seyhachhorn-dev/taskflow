package com.seyha.taskflow.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

public record TaskEvent(
        UUID taskId,
        String title,
        String assigneeEmail,
        String actorEmail,
        String eventType,      // CREATED, ASSIGNED, COMPLETED
        LocalDateTime timestamp
) implements Serializable {}