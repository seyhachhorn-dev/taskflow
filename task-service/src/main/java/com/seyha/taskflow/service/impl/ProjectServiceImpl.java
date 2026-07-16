package com.seyha.taskflow.service.impl;

import com.seyha.taskflow.domain.Project;
import com.seyha.taskflow.domain.User;
import com.seyha.taskflow.dto.project.ProjectRequest;
import com.seyha.taskflow.dto.project.ProjectResponse;
import com.seyha.taskflow.repository.ProjectRepository;
import com.seyha.taskflow.service.ProjectService;
import com.seyha.taskflow.utils.SecurityUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponse create(ProjectRequest req) {
        User user = SecurityUtils.getCurrentUser();
        var project = Project.builder()
                .name(req.name())
                .description(req.description())
                .owner(user)
                .build();
        return ProjectResponse.from(projectRepository.save(project));
    }

    @Override
    public List<ProjectResponse> findMine() {
        User user = SecurityUtils.getCurrentUser();
        return projectRepository.findByOwnerId(user.getId())
                .stream().map(ProjectResponse::from).toList();
    }

    @Override
    public ProjectResponse findById(UUID id) {
        return ProjectResponse.from(getOrThrow(id));
    }

    @Override
    public void delete(UUID id) throws AccessDeniedException {
        User user = SecurityUtils.getCurrentUser();
        Project project = getOrThrow(id);
        if (!project.getOwner().getId().equals(user.getId())) {
            throw new AccessDeniedException("Only the owner can delete this project");
        }
        projectRepository.delete(project);
    }

    @Override
    public Project getOrThrow(UUID id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found: " + id));
    }
}
