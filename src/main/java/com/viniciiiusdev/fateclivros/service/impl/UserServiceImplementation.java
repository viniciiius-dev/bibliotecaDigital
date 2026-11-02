package com.viniciiiusdev.fateclivros.service.impl;

import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.repository.UserRepository;
import com.viniciiiusdev.fateclivros.dtos.AddUserDTO;
import com.viniciiiusdev.fateclivros.entity.User;
import com.viniciiiusdev.fateclivros.service.BookService;
import com.viniciiiusdev.fateclivros.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final BookService bookService;

    public UserServiceImplementation(UserRepository userRepository, BookService bookService){
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    public User getUser(Long id){
        return userRepository.getReferenceById(id);
    }

    public void addUser(AddUserDTO addUserDTO){
        User newUser = new User(addUserDTO.name(), addUserDTO.registroAluno());
        userRepository.saveAndFlush(newUser);
    }

    public void addFavorite(Long userId, Long bookId){
        User user = userRepository.getReferenceById(userId);
        Book book = bookService.getBookById(bookId);

        user.getFavoriteBooks().add(book);
    }

    public void removeFavorite(Long userId, Long bookId){
        User user = userRepository.getReferenceById(userId);
        Book book = bookService.getBookById(bookId);

        user.getFavoriteBooks().remove(book);
    }

    public Set<Book> getFavoriteByUserId(Long userId){
        User user = userRepository.getReferenceById(userId);
        return user.getFavoriteBooks();
    }
}

