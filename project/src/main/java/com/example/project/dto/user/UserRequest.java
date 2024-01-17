package com.example.project.dto.user;

import com.example.project.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserRequest {
    private String email;
    private String name;
    private String password;
    private Integer age;
    private Role role;
}
