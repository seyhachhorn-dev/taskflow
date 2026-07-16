package com.seyha.taskflow.service;

import com.seyha.taskflow.domain.Task;
import com.seyha.taskflow.dto.task.TaskRequest;
import com.seyha.taskflow.dto.task.TaskResponse;
import com.seyha.taskflow.enums.TaskStatus;

import java.util.List;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

public interface TaskService {
    TaskResponse create(TaskRequest req);
    List<TaskResponse> findByProject(UUID projectId);
    TaskResponse update(UUID id, TaskRequest req);
    TaskResponse changeStatus(UUID id, TaskStatus status);
    TaskResponse assign(UUID id, UUID userId);
    void delete(UUID id);
    Task getOrThrow(UUID id);
}
