package com.example.project.dto.book;
import com.example.project.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BookRequest {
    private String description;
    private Integer price;
    private String transcript;
    private Integer AgeAccess;
    private String name;
    private String genre;
    private String author_name;
    private Integer created_date;
    private Role role;
}
