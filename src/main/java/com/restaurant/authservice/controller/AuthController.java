package com.restaurant.authservice.controller;

import com.restaurant.authservice.dto.LoginRequest;
import com.restaurant.authservice.dto.LoginResponse;
import com.restaurant.authservice.dto.SignupRequest;
import com.restaurant.authservice.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request){

        return authService.signup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){

        String token = authService.login(request);

        return new LoginResponse(token);
    }
}