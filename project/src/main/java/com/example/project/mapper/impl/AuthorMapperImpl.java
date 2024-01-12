package com.example.project.mapper.impl;

import com.example.project.dto.author.AuthorResponse;
import com.example.project.dto.book.AuthorResponse;
import com.example.project.entites.Author;
import com.example.project.mapper.AuthorMapper;

import java.util.ArrayList;
import java.util.List;

public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorResponse toDto(Author object) {
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setBookName(object.getBookName());
        authorResponse.setName(object.getName());
        return authorResponse;

    }

    @Override
    public List<AuthorResponse> toDtoS(List<Author> authors) {
        List<AuthorResponse> authorResponses = new ArrayList<>();
        for(Author author: authors){
            authorResponses.add(toDto(author));
        }
        return authorResponses;
    }
}
