package com.example.project.service.auth;

import com.example.project.dto.auth.AuthLoginRequest;
import com.example.project.dto.auth.AuthLoginResponse;
import com.example.project.dto.user.UserRequest;
import com.example.project.entites.User;
import org.springframework.stereotype.Service;



public interface AuthService {
    void register(UserRequest userRequest);

    AuthLoginResponse login(AuthLoginRequest authLoginRequest);

    User getUsernameFromToken(String token);
}
