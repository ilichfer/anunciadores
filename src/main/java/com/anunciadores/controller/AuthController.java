package com.anunciadores.controller;

import com.anunciadores.auth.dto.LoginRequest;
import com.anunciadores.auth.dto.LoginResponse;
import com.anunciadores.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // POST /api/auth/login
    // Body: { "cedula": "12345678", "password": "miPassword" }
    @PostMapping("/loginReact")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(401)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
