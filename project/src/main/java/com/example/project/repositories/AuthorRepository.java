package com.example.project.repositories;

import com.example.project.entites.Author;
import com.example.project.entites.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository

extends JpaRepository<Author, Long> {

}
