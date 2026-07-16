package com.seyha.taskflow.repository;

import com.seyha.taskflow.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Project> findByOwnerId(UUID ownerId);
}
