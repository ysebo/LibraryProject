package com.example.project.service.book.impl;

import com.example.project.dto.book.BookRequest;
import com.example.project.dto.book.BookResponse;
import com.example.project.entites.Book;
import com.example.project.service.auth.AuthService;
import com.example.project.entites.User;
import com.example.project.enums.Role;
import com.example.project.exception.BadCredentialsException;
import com.example.project.exception.BadRequestException;
import com.example.project.exception.NotFoundException;
import com.example.project.repositories.BookRepository;
import com.example.project.mapper.BookMapper;
import com.example.project.repositories.UserRepository;
import com.example.project.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final AuthService authService;


    @Override
    public void register(BookRequest bookRequest) {
        if(bookRequest.getName().isEmpty()){
            throw new NotFoundException("Book name can't be empty" , HttpStatus.BAD_REQUEST);
        }

        Book book= new Book();
        book.setName(bookRequest.getName());
        book.setPrice(bookRequest.getPrice());
        book.setGenre(bookRequest.getGenre());
        book.setTranscript(bookRequest.getTranscript());
        book.setAgeAccess(bookRequest.getAgeAccess());
        book.setAuthor_name(bookRequest.getAuthor_name());
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
            throw new NotFoundException("the book with id: "+id+" is empty!", HttpStatus.BAD_REQUEST);
        else{
            book.get().setName(bookRequest.getName());
            book.get().setDescription(bookRequest.getDescription());
            book.get().setPrice(bookRequest.getPrice());
            bookRepository.save(book.get());

        }
    }

    @Override
    public List<BookResponse> getAll(String s)
    {
        User user = authService.getUsernameFromToken(s);
        if (!user.getRole().equals(Role.ADMIN)){
            System.out.println("the user");
            List<BookResponse> bookResponses = mapper.toDtoS(bookRepository.findAllByExistAndAgeAccessLessThan(
                    true, user.getCustomer().getAge()));
            System.out.println("the size: "+bookResponses.size());
            return bookResponses;
        }
        System.out.println("the admin");

        return mapper.toDtoS(bookRepository.findAll());
    }
    public BookResponse getById(Long id) {
        Optional<Book> book= bookRepository.findById(id);
        if (book.isEmpty())
            throw new NotFoundException("book with id: "+id+" not found!", HttpStatus.BAD_GATEWAY);

        BookResponse bookResponse = new BookResponse();
        bookResponse.setName(book.get().getName());

        return  mapper.toDto(book.get());
    }

    @Override
    public void add(BookRequest request, String token) {
        if (bookRepository.findByTranscript(request.getTranscript()).isPresent())
            throw new NotFoundException("book with this transcript is already exist!: "+ request.getTranscript(),
                    HttpStatus.BAD_REQUEST);
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function only for admin!");

        Book book = new Book();

        book.setName(request.getName());
        book.setPrice(request.getPrice());
        book.setAgeAccess(request.getAgeAccess());
        book.setDescription(request.getDescription());
        book.setAuthor_name(request.getAuthor_name());
        book.setGenre(request.getGenre());
        book.setTranscript(request.getTranscript());
        book.setCreated_date(request.getCreated_date());
        book.setExist(true);
        bookRepository.save(book);
    }

    @Override
    public void buy(Long bookId, String token) {
        User user = authService.getUsernameFromToken(token);
        Optional<Book>book  = bookRepository.findById(bookId);
        if(book.isEmpty())
            throw new NotFoundException("this book has already been sold", HttpStatus.BAD_REQUEST);
        book.get().setExist(false);
        List<Book>books = new ArrayList<>();
        if (!user.getCustomer().getBooks().isEmpty())
            books = user.getCustomer().getBooks();
        books.add(book.get());
        user.getCustomer().setBooks(books);
        userRepository.save(user);
    }

    @Override
    public List<BookResponse> getMyBooks(String string) {
        User user = authService.getUsernameFromToken(string);

        if (!user.getRole().equals(Role.ADMIN)){
            System.out.println("the user");
            List<BookResponse> bookResponses = mapper.toDtoS(user.getCustomer().getBooks());
            System.out.println("the size: "+bookResponses.size());
            return bookResponses;
        }
        return null;
    }

    private boolean containsType(String type) {
        for (Role role1 : Role.values()){
            if (role1.name().equalsIgnoreCase(type))
                return true;
        }
        return false;
    }
}

