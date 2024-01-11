package com.example.project.mapper;
import com.example.project.dto.book.BookResponse;
import com.example.project.entites.Book;
import java.util.List;

public interface BookMapper {
    BookResponse toDto(Book product);
    List<BookResponse> toDtoS(List<Book> all);
}
