package com.seyha.taskflow.dto.task;

import com.seyha.taskflow.enums.PriorityStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

public record TaskRequest(@NotBlank String title,
                          String description,
                          PriorityStatus priority,
                          LocalDate deadline,
                          @NotNull UUID projectId) {}