package com.seyha.taskflow.repository;

import com.seyha.taskflow.domain.Task;

import java.util.List;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

public interface TaskRepository {
    List<Task> findByProjectId(UUID projectId);
    List<Task> findByAssigneeId(UUID assigneeId);
}
