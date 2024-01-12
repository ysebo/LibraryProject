package com.example.project.service.book;
import com.example.project.dto.book.*;
import java.util.List;

public interface BookService {
    BookResponse getById(Long id);

    void register(BookRequest productRequest);
    void deleteById(Long id );
    void updateById(Long id , BookRequest productRequest);
    List<BookResponse> getAll();

    void add(BookRequest productRequest, Long userId);
}
