package com.seyha.taskflow.dto.task;

import com.seyha.taskflow.domain.Task;
import com.seyha.taskflow.enums.PriorityStatus;
import com.seyha.taskflow.enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

public record TaskResponse(UUID id, String title, String description,
                           TaskStatus status, PriorityStatus priority,
                           LocalDate deadline, UUID projectId,
                           String assigneeEmail, LocalDateTime createdAt) {

    public static TaskResponse from(Task t) {
        return new TaskResponse(t.getId(), t.getTitle(), t.getDescription(),
                t.getStatus(), t.getPriority(), t.getDeadline(),
                t.getProject().getId(),
                t.getAssignee() != null ? t.getAssignee().getEmail() : null,
                t.getCreatedAt());
    }
}