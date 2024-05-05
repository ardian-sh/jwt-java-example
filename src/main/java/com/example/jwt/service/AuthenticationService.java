package com.example.jwt.service;


import com.example.jwt.request.LoginRequest;
import com.example.jwt.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity<?> register(RegisterRequest request);

    ResponseEntity<?> login(LoginRequest request);
}
