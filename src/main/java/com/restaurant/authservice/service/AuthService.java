package com.restaurant.authservice.service;

import com.restaurant.authservice.dto.LoginRequest;
import com.restaurant.authservice.dto.SignupRequest;

public interface AuthService {


    String signup(SignupRequest request);

    String login(LoginRequest request);

}