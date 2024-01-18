package com.example.project.service.book;
import com.example.project.dto.book.*;
import java.util.List;

public interface BookService {
    BookResponse getById(Long id);

    void register(BookRequest productRequest);
    void deleteById(Long id );
    void updateById(Long id , BookRequest productRequest);
    List<BookResponse> getAll(String s );

    void add(BookRequest productRequest, String token);

    void buy(Long bookId, String token);

    List<BookResponse> getMyBooks(String string);
}
