package com.seyha.taskflow.dto.project;

import jakarta.validation.constraints.NotBlank;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

public record ProjectRequest (@NotBlank String name, String description) {}
