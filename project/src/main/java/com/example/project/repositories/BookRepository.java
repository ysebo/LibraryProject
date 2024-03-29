package com.example.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.project.entites.Book;

import java.util.List;
import java.util.Optional;

@Repository

public interface
BookRepository extends JpaRepository<Book , Long> {
    Optional<Book> findByTranscript(String transcript );
    List<Book> findAllByExistAndAgeAccessLessThan(Boolean b , Integer integer);
}
