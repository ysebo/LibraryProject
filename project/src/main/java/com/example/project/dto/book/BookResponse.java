package com.example.project.dto.book;
import com.example.project.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BookResponse {
    private Long id ;
    private String description;
    private Integer price;
    private String name;
    private String author;
    private Integer created_date;
    private Role role;
}
