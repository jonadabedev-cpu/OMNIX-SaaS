package com.omnix.services;

import com.omnix.dto.AuthResponse;
import com.omnix.dto.LoginRequest;
import com.omnix.dto.RegisterRequest;
import com.omnix.dto.UserResponse;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    UserResponse register(RegisterRequest request);
}