package com.example.project.service.user;

import com.example.project.dto.user.*;

public interface UserService {


    UserResponse getById(Long id);
    void deleteById(Long id);
    void updateById(Long id, UserRequest userRequest);




    void register(UserRequest userRequest);


}
