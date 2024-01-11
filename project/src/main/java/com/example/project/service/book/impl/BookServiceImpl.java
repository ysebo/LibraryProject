package com.example.project.service.book.impl;

import com.example.project.dto.book.BookRequest;
import com.example.project.dto.book.BookResponse;
import com.example.project.entites.Book;
//import com.example.project.exception.BadRequestException;
import com.example.project.entites.User;
import com.example.project.enums.Type;
import com.example.project.exception.BadRequestException;
import com.example.project.exception.NotFoundException;
import com.example.project.repositories.BookRepository;
import com.example.project.mapper.BookMapper;
import com.example.project.repositories.UserRepository;
import com.example.project.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper mapper;
    private final UserRepository userRepository;


    @Override
    public void register(BookRequest bookRequest) {
        if(bookRequest.getName().contains("@")){
            throw new NotFoundException("You are fucking idiot " , HttpStatus.BAD_GATEWAY);
        }

        Book book= new Book();
        book.setName(bookRequest.getName());
        book.setType(bookRequest.getType());
        book.setPrice(bookRequest.getPrice());
        book.setAuthor(bookRequest.getAuthor());
        book.setCreated_date(bookRequest.getCreated_date());
        book.setDescription(bookRequest.getDescription());
        bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Book > book= bookRepository.findById(id);
        if(book.isEmpty()) {
            throw new NotFoundException("There is no book" , HttpStatus.BAD_GATEWAY);
        }
        else {
            bookRepository.deleteById(id);
        }


    }

    @Override
    public void updateById(Long id, BookRequest bookRequest) {
        Optional<Book > book= bookRepository.findById(id);
        if (book.isEmpty())
            throw new NotFoundException("the bookwith id: "+id+" is empty!", HttpStatus.BAD_REQUEST);
        else{
            book.get().setName(bookRequest.getName());
            book.get().setType(bookRequest.getType());
            book.get().setDescription(bookRequest.getDescription());
            book.get().setPrice(bookRequest.getPrice());
            bookRepository.save(book.get());
        }
    }

    @Override
    public List<BookResponse> getAll() {
        return  mapper.toDtoS(bookRepository.findAll());
    }
    public BookResponse getById(Long id) {
        Optional<Book> book= bookRepository.findById(id);
        if (book.isEmpty())
            throw new NotFoundException("bookwith id: "+id+" not found!", HttpStatus.BAD_GATEWAY);

        BookResponse bookResponse = new BookResponse();
        bookResponse.setName(book.get().getName());
        return  mapper.toDto(book.get());
    }

//    @Override
//    public void add(BookRequest bookRequest, Long userId) {
//        Book book= new Book();
//        Optional<User> user = userRepository.findById(userId);
//        book.setName(bookRequest.getName());
//        book.setDescription(bookRequest.getDescription());
//        book.setPrice(bookRequest.getPrice());
//        book.setCreated_date(bookRequest.getCreated_date());
//        if(user.isEmpty()){
//            throw new NotFoundException("Sanjar is gay " , HttpStatus.NOT_FOUND);
//        }
//        book.setOwner(user.get());
//
//
//        if (!containsType(String.valueOf(bookRequest.getType())))
//            throw new BadRequestException("no type with name: "+bookRequest.getType()+"!");
//        book.setType(Type.valueOf(String.valueOf(bookRequest.getType())));
//
//        List<Book> books = new ArrayList<>();
//        if (user.get().getUserBooks()!=null){
//            books = user.get().getUserBooks();
//        }
//        books.add(book);
//        user.get().setUserBooks(books);
//
//
//
//        bookRepository.save(book);
//    }
    private boolean containsType(String type) {
        for (Type type1:Type.values()){
            if (type1.name().equalsIgnoreCase(type))
                return true;
        }
        return false;
    }
}

