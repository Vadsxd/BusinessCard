package ru.domain.businesscard.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.domain.businesscard.requests.auth.AuthRequest;
import ru.domain.businesscard.responses.AuthResponse;
import ru.domain.businesscard.services.AuthService;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid AuthRequest request) {
        return ResponseEntity.ok(authService.signUp(request));
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> signIn(@RequestBody @Valid AuthRequest request) {
        return ResponseEntity.ok(authService.signIn(request));
    }
}
