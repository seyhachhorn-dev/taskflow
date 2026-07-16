package com.seyha.taskflow.controller;

import com.seyha.taskflow.base.ApiStructureResponse;
import com.seyha.taskflow.base.BaseController;
import com.seyha.taskflow.dto.auth.req.AuthenticationRequest;
import com.seyha.taskflow.dto.auth.req.RegisterRequest;
import com.seyha.taskflow.dto.auth.res.AuthenticationResponse;
import com.seyha.taskflow.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Developed by ChhornSeyha
 * Date: 15/02/2026
 */

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController extends BaseController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiStructureResponse<Void>> register(@Valid @RequestBody RegisterRequest req) {
        return response(HttpStatus.CREATED, authenticationService.register(req));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ApiStructureResponse<AuthenticationResponse>> authenticate(@Valid @RequestBody AuthenticationRequest req) {
        return ok("Authenticated successfully", authenticationService.authenticate(req));
    }

//    @PostMapping("/refresh-token")
//    public void refreshToken(HttpServletRequest request,
//                             HttpServletResponse response) throws IOException {
//       authenticationService.refreshToken(request,response);
//    }

}
