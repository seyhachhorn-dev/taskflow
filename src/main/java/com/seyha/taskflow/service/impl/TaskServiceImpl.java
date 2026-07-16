package com.seyha.taskflow.service.impl;

import com.seyha.taskflow.domain.Task;
import com.seyha.taskflow.domain.User;
import com.seyha.taskflow.dto.task.TaskRequest;
import com.seyha.taskflow.dto.task.TaskResponse;
import com.seyha.taskflow.enums.PriorityStatus;
import com.seyha.taskflow.enums.TaskStatus;
import com.seyha.taskflow.repository.ProjectRepository;
import com.seyha.taskflow.repository.TaskRepository;
import com.seyha.taskflow.repository.UserRepository;
import com.seyha.taskflow.service.ProjectService;
import com.seyha.taskflow.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final ProjectService projectService;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    @Override
    public TaskResponse create(TaskRequest req) {
        var task = Task.builder()
                .title(req.title())
                .description(req.description())
                .priority(req.priority() != null ? req.priority() : PriorityStatus.MEDIUM)
                .deadline(req.deadline())
                .project(projectService.getOrThrow(req.projectId()))
                .build();
        Task saved = taskRepository.save(task);
        // Sprint 2: publish "task.created" event here
        return TaskResponse.from(saved);
    }

    @Override
    public List<TaskResponse> findByProject(UUID projectId) {
        return taskRepository.findByProjectId(projectId)
                .stream().map(TaskResponse::from).toList();
    }

    @Override
    public TaskResponse update(UUID id, TaskRequest req) {
        Task task = getOrThrow(id);
        task.setTitle(req.title());
        task.setDescription(req.description());
        if (req.priority() != null) task.setPriority(req.priority());
        task.setDeadline(req.deadline());
        return TaskResponse.from(taskRepository.save(task));
    }

    @Override
    public TaskResponse changeStatus(UUID id, TaskStatus status) {
        Task task = getOrThrow(id);
        task.setStatus(status);
        Task saved = taskRepository.save(task);
        // Sprint 2: publish "task.completed" event when status == DONE
        return TaskResponse.from(saved);
    }

    @Override
    public TaskResponse assign(UUID id, UUID userId) {
        Task task = getOrThrow(id);
        User assignee = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
        task.setAssignee(assignee);
        Task saved = taskRepository.save(task);
        // Sprint 2: publish "task.assigned" event here
        return TaskResponse.from(saved);
    }

    @Override
    public void delete(UUID id) {
        taskRepository.delete(getOrThrow(id));
    }

    @Override
    public Task getOrThrow(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found: " + id));
    }
}
