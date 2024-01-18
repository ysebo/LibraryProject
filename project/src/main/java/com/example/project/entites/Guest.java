package com.example.project.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "guest_table")
public class Guest {
   @Id
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id ;
    private String name;
    private String interests;
    @OneToOne(mappedBy = "guest")
    private User user;
}
