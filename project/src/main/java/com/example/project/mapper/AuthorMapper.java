package com.example.project.mapper;

import com.example.project.dto.author.AuthorResponse;
import com.example.project.entites.Author;

import java.util.List;

public interface AuthorMapper {
    AuthorResponse toDto(Author product);
    List<AuthorResponse> toDtoS(List<Author> all);
}
