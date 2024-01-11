package com.example.project.mapper;

import com.example.project.dto.user.UserResponse;
import com.example.project.entites.User;

import java.util.List;

public interface UserMapper {
    UserResponse toDto(User object);

    List<UserResponse> toDtos(List<User> users);
}
