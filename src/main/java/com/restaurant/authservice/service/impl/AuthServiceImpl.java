package com.restaurant.authservice.service.impl;

import com.restaurant.authservice.dto.LoginRequest;
import com.restaurant.authservice.dto.SignupRequest;
import com.restaurant.authservice.exception.ResourceNotFoundException;
import com.restaurant.authservice.model.User;
import com.restaurant.authservice.repository.UserRepository;
import com.restaurant.authservice.service.AuthService;
import com.restaurant.authservice.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String signup(SignupRequest request) {

        if(userRepository.existsByUsername(request.getUsername())){
            throw new ResourceNotFoundException("User already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());

        // password encrypted
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public String login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ResourceNotFoundException("Invalid password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}