package com.seyha.taskflow.service;

import com.seyha.taskflow.dto.auth.req.AuthenticationRequest;
import com.seyha.taskflow.dto.auth.req.RegisterRequest;
import com.seyha.taskflow.dto.auth.res.AuthenticationResponse;

/**
 * Developed by ChhornSeyha
 * Date: 15/02/2026
 */

public interface AuthenticationService {
    String register(RegisterRequest req);
    AuthenticationResponse authenticate(AuthenticationRequest req);
}
