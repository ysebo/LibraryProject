package com.example.project.entites;
import com.example.project.entites.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id ;
    private String name ;
    private String bookName ;
    @OneToMany(cascade = CascadeType.ALL)
    List<Book> AuthorBooks;
}
