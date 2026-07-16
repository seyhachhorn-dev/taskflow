package com.seyha.taskflow.service;

import com.seyha.taskflow.domain.Project;
import com.seyha.taskflow.domain.User;
import com.seyha.taskflow.dto.project.ProjectRequest;
import com.seyha.taskflow.dto.project.ProjectResponse;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

public interface ProjectService {
    ProjectResponse create(ProjectRequest req);
    List<ProjectResponse> findMine();
    ProjectResponse findById(UUID id);
    void delete(UUID id) throws AccessDeniedException;
    Project getOrThrow(UUID id);
}
