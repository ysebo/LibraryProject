package com.example.project.service.author.impl;

import com.example.project.dto.author.AuthorRequest;
import com.example.project.dto.author.AuthorResponse;
import com.example.project.entites.Author;
import com.example.project.entites.User;
import com.example.project.enums.Type;
import com.example.project.exception.BadRequestException;
import com.example.project.exception.NotFoundException;
import com.example.project.mapper.AuthorMapper;
import com.example.project.repositories.AuthorRepository;
import com.example.project.repositories.UserRepository;
import com.example.project.service.author.AuthorService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;
    private final UserRepository userRepository;
        @Override
        public void register(AuthorRequest authorRequest) {
            if(authorRequest.getName().contains("@")){
                throw new NotFoundException("You are fucking idiot " , HttpStatus.BAD_GATEWAY);
            }

            Author author= new Author();
            author.setName(authorRequest.getName());
            author.setBookName(authorRequest.getBookName());

            authorRepository.save(author);
        }

        @Override
        public void deleteById(Long id) {
            Optional<Author > author= authorRepository.findById(id);
            if(author.isEmpty()) {
                throw new NotFoundException("There is no author" , HttpStatus.BAD_GATEWAY);
            }
            else {
                authorRepository.deleteById(id);
            }


        }

        @Override
        public void updateById(Long id, AuthorRequest authorRequest) {
            Optional<Author > author= authorRepository.findById(id);
            if (author.isEmpty())
                throw new NotFoundException("the author with id: "+id+" is empty!", HttpStatus.BAD_REQUEST);
            else{
                author.get().setName(authorRequest.getName());
                author.get().setBookName(authorRequest.getBookName());
                authorRepository.save(author.get());
            }
        }

        @Override
        public List<AuthorResponse> getAll() {
            return  mapper.toDtoS(authorRepository.findAll());
        }
        public AuthorResponse getById(Long id) {
            Optional<Author> author= authorRepository.findById(id);
            if (author.isEmpty())
                throw new NotFoundException("author with id: "+id+" not found!", HttpStatus.BAD_GATEWAY);

            AuthorResponse authorResponse = new AuthorResponse();
            authorResponse.setName(author.get().getName());
            authorResponse.setBookName(author.get().getBookName());

            return  mapper.toDto(author.get());
        }

        @Override
        public void   add(AuthorRequest authorRequest, Long userId) {
            Author author= new Author();
            Optional<User> user = userRepository.findById(userId);
            author.setName(authorRequest.getName());
            author.setBookName(authorRequest.getBookName());
            if(user.isEmpty()){
                throw new NotFoundException("Sanjar is gay " , HttpStatus.NOT_FOUND);
            }
            author.setOwner(user.get());


            if (!containsType(String.valueOf(authorRequest.getType())))
                throw new BadRequestException("no type with name: "+authorRequest.getType()+"!");
            author.setType(Type.valueOf(String.valueOf(authorRequest.getType())));

            List<Author> authors = new ArrayList<>();
            if (user.get().getUserAuthors()!=null){
                authors = user.get().getUserAuthors();
            }
            authors.add(author);
            user.get().setUserAuthors(authors);



            authorRepository.save(author);
        }
        private boolean containsType(String type) {
            for (Type type1:Type.values()){
                if (type1.name().equalsIgnoreCase(type))
                    return true;
            }
            return false;
        }



}
