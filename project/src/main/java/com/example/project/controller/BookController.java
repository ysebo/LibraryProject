package com.example.project.controller;
import com.example.project.dto.book.BookResponse;
import com.example.project.dto.book.BookRequest;
import com.example.project.entites.User;
import com.example.project.repositories.BookRepository;
import com.example.project.repositories.UserRepository;
import com.example.project.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.project.mapper.BookMapper;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@AllArgsConstructor

public class BookController {
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookMapper bookMapper;

    @PostMapping("/register")
    public void register(@RequestBody BookRequest bookRequest){
        bookService.register(bookRequest);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        bookService.deleteById(id);
    }
    @PutMapping("/update/{id}")
    public void update(@RequestBody BookRequest bookRequest, @PathVariable Long id ){
        bookService.updateById(id, bookRequest);
    }

    @PostMapping("/add")
    public void add(@RequestBody BookRequest request, @RequestHeader("Authorization") String token){
        bookService.add(request, token );
    }
    @GetMapping("/get/{id}")
    public BookResponse bookResponse(@PathVariable Long id){
        return bookService.getById(id);
    }
    @GetMapping("/{userId}")
    public List<BookResponse>userBooks(@PathVariable Long userId){
        Optional<User> user = userRepository.findById(userId);
        return bookMapper.toDtoS(user.get().getCustomer().getBooks());
    }
    @PostMapping("/buy/{bookId}")
    public void buy(@PathVariable Long bookId , @RequestHeader("Authorization")String token){
        bookService.buy(bookId , token );
    }
    @GetMapping("/my/books")
    public List<BookResponse> myBooks(@RequestHeader("Authorization") String string) {
        return bookService.getMyBooks(string);
    }
    @GetMapping("/books")
    public List<BookResponse> bookResponses(@RequestHeader("Authorization") String string){
        return bookService.getAll(string);
    }
}
