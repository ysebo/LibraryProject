package com.example.project.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Entity

@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    List<Book> userBooks;
    
}
