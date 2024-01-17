package com.example.project.service.user.impl;

import com.example.project.dto.user.UserRequest;
import com.example.project.dto.user.UserResponse;
import com.example.project.entites.User;
import com.example.project.exception.NotFoundException;
import com.example.project.repositories.UserRepository;
import com.example.project.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            System.out.println("user is empty!");
        }
        else {
            UserResponse userResponse = new UserResponse();
            userResponse.setEmail(user.get().getEmail());
            return userResponse;

        }
        return null;
    }

    @Override
    public void  deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            System.out.println("user is empty!");
        }
        else {
            userRepository.deleteById(id);
        }

    }

    @Override
    public void updateById(Long id, UserRequest userRequest) {
            Optional<User> user = userRepository.findById(id);
            if
            (user.isEmpty()){
                System.out.println("user is empty!");
            }
            else {

                user.get().setPassword(userRequest.getPassword());
                user.get().setEmail(userRequest.getEmail());
                userRepository.save(user.get());

            }
    }


    @Override
    public void register(UserRequest userRequest) {
        if (userRequest.getEmail().isEmpty())
            throw new NotFoundException("Username can't be empty" , HttpStatus.BAD_GATEWAY);
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());


        userRepository.save(user);
    }
}
