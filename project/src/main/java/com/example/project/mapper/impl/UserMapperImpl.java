package com.example.project.mapper.impl;
import com.example.project.dto.user.UserResponse;
import com.example.project.entites.User;
import com.example.project.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toDto(User object) {
        UserResponse userResponse = new UserResponse();

        userResponse.setEmail(object.getEmail());
        return userResponse;
    }
    @Override
    public List<UserResponse>toDtos(List<User> users){
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users){
            userResponses.add(toDto(user));
        }
        return userResponses;
    }

}
