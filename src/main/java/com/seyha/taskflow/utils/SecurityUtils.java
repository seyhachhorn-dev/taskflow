package com.seyha.taskflow.utils;

import com.seyha.taskflow.core.security.SecurityUser;
import com.seyha.taskflow.domain.User;
import com.seyha.taskflow.enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Developed by ChhornSeyha
 * Date: 16/07/2026
 */

@Component
public final class SecurityUtils {
    public static User getCurrentUser() {
        return findCurrentUser().orElseThrow(
                () -> new IllegalStateException("No authenticated user in security context"));
    }

    /** Returns the current user if present (empty for public endpoints). */
    public static Optional<User> findCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof SecurityUser securityUser) {
            return Optional.of(securityUser.getUser());
        }
        return Optional.empty();
    }

    public static UUID getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public static String getCurrentUserEmail() {
        return getCurrentUser().getEmail();
    }

    public static boolean isAdmin() {
        return findCurrentUser()
                .map(u -> u.getRole() == Role.ADMIN)
                .orElse(false);
    }
}
