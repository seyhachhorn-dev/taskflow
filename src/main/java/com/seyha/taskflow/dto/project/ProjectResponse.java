package com.seyha.taskflow.dto.project;

import com.seyha.taskflow.domain.Project;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

public record ProjectResponse(UUID id, String name, String description,
                              String ownerEmail, LocalDateTime createdAt) {

    public static ProjectResponse from(Project p) {
        return new ProjectResponse(p.getId(), p.getName(), p.getDescription(),
                p.getOwner().getEmail(), p.getCreatedAt());
    }
}