package com.example.project.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "customer_table ")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer age;
    private String name;
    private Integer balance;
    @OneToOne(mappedBy = "customer")
    private User user;
    @OneToMany()
    private List<Book>books;


}
