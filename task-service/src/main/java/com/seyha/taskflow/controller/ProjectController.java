package com.seyha.taskflow.controller;

import com.seyha.taskflow.base.ApiStructureResponse;
import com.seyha.taskflow.base.BaseController;
import com.seyha.taskflow.dto.project.ProjectRequest;
import com.seyha.taskflow.dto.project.ProjectResponse;
import com.seyha.taskflow.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController extends BaseController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiStructureResponse<ProjectResponse>> create(@Valid @RequestBody ProjectRequest req) {
        return created("Project created successfully", projectService.create(req));
    }

    @GetMapping
    public ResponseEntity<ApiStructureResponse<List<ProjectResponse>>> myProjects() {
        return ok("Projects retrieved successfully", projectService.findMine());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiStructureResponse<ProjectResponse>> byId(@PathVariable UUID id) {
        return ok("Project retrieved successfully", projectService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiStructureResponse<Void>> delete(@PathVariable UUID id) throws AccessDeniedException {
        projectService.delete(id);
        return ok("Project deleted successfully");
    }
}
