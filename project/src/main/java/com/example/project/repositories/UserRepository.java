package com.example.project.repositories;

import com.example.project.entites.Book;
import com.example.project.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface
UserRepository extends JpaRepository<User, Long> {

}