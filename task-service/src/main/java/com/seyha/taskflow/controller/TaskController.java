package com.seyha.taskflow.controller;

import com.seyha.taskflow.base.ApiStructureResponse;
import com.seyha.taskflow.base.BaseController;
import com.seyha.taskflow.dto.task.TaskRequest;
import com.seyha.taskflow.dto.task.TaskResponse;
import com.seyha.taskflow.enums.TaskStatus;
import com.seyha.taskflow.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController extends BaseController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiStructureResponse<TaskResponse>> create(@Valid @RequestBody TaskRequest req) {
        return created("Task created successfully", taskService.create(req));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiStructureResponse<List<TaskResponse>>> findByProject(@PathVariable UUID projectId) {
        return ok("Tasks retrieved successfully", taskService.findByProject(projectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiStructureResponse<TaskResponse>> update(@PathVariable UUID id,
                                                                      @Valid @RequestBody TaskRequest req) {
        return ok("Task updated successfully", taskService.update(id, req));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiStructureResponse<TaskResponse>> changeStatus(@PathVariable UUID id,
                                                                            @RequestBody TaskStatus status) {
        return ok("Task status updated successfully", taskService.changeStatus(id, status));
    }

    @PatchMapping("/{id}/assign/{userId}")
    public ResponseEntity<ApiStructureResponse<TaskResponse>> assign(@PathVariable UUID id,
                                                                      @PathVariable UUID userId) {
        return ok("Task assigned successfully", taskService.assign(id, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiStructureResponse<Void>> delete(@PathVariable UUID id) {
        taskService.delete(id);
        return ok("Task deleted successfully");
    }
}
