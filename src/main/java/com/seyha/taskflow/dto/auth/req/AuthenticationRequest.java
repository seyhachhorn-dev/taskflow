package com.seyha.taskflow.dto.auth.req;

/**
 * Developed by ChhornSeyha
 * Date: 15/02/2026
 */

public record AuthenticationRequest(
        String email,
        String password
) {
}
