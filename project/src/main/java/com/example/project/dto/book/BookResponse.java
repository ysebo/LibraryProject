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
    private String genre;
    private String name;
    private String author_name;
    private Integer created_date;
    private Integer AgeAccess;
}
