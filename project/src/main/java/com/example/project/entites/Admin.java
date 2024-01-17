//package com.example.project.entites;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//@Getter
//@Setter
//@Entity
//public class Admin {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Integer age;
//    private String name;
//    @OneToOne(mappedBy = "admin")
//    private User user;
//    @OneToMany()
//    private List<Book> books;
//}
