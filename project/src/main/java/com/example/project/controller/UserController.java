package com.example.project.controller;


import com.example.project.dto.user.UserRequest;
import com.example.project.dto.user.UserResponse;
import com.example.project.repositories.UserRepository;
import com.example.project.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;



    @PostMapping("register")
    public void register(@RequestBody UserRequest userRequest){
        userService.register(userRequest);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id ){
        userService.deleteById(id);

    }
    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id ,@RequestBody UserRequest userRequest){
        userService.updateById(id, userRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id){
        return userService.getById(id);
    }
    @PutMapping("/update2/{id}")
    public void updateUser2(@PathVariable Long id, @RequestBody UserRequest userRequest){
        userService.updateById(id, userRequest);
    }







}