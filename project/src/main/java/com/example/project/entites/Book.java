package com.example.project.entites;
import com.example.project.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String transcript;
    private String description;
    private Integer price;
    private String name;
    private String author_name;
    private Integer created_date;
    private Integer age_access;
    private Boolean exist = true;
    @ManyToOne(cascade = CascadeType.ALL)
    User book  ;


}
