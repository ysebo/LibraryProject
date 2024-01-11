package com.example.project.entites;
import com.example.project.enums.Type;
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
    private String description;
    private Integer price;
    private String name;
    private String author;
    private Integer created_date;
    @Enumerated(EnumType.STRING)
    private Type type;
}
