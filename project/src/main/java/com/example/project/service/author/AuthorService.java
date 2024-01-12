package com.example.project.service.author;

import com.example.project.dto.author.AuthorResponse;
import com.example.project.dto.author.AuthorRequest;
import com.example.project.dto.author.AuthorResponse;

import java.util.List;

public interface AuthorService {
    AuthorResponse getById(Long id);

    void register(AuthorRequest productRequest);
    void deleteById(Long id );
    void updateById(Long id , AuthorRequest productRequest);
    List<AuthorResponse> getAll();

    void add(AuthorRequest productRequest, Long userId);
}
