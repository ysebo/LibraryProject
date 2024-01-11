package com.example.project.mapper.impl;
import com.example.project.dto.book.BookResponse;
import com.example.project.entites.Book;
import com.example.project.mapper.BookMapper;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component

public class BookMapperImpl implements BookMapper {

    @Override
    public BookResponse toDto(Book object) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setType(object.getType());
        bookResponse.setName(object.getName());
        bookResponse.setDescription(object.getDescription());
        bookResponse.setPrice(object.getPrice());
        bookResponse.setCreated_date(object.getCreated_date());
        return bookResponse;
    }



    @Override
    public List<BookResponse>toDtoS(List<Book>books){
        List<BookResponse> bookResponses = new ArrayList<>();
        for(Book book:books){
            bookResponses.add(toDto(book));
        }
        return bookResponses;
    }
}
