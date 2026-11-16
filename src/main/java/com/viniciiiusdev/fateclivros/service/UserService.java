package com.viniciiiusdev.fateclivros.service;

import com.viniciiiusdev.fateclivros.dtos.AddUserDTO;
import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.entity.User;

import java.util.Set;

public interface UserService {
    User getUser(Long id);
    void addUser(AddUserDTO userDTO);
    Set<Book> getFavoritesBook();
    void addFavoriteBook(Long bookId) throws RuntimeException;
    void removeFavoriteBook(Long bookId) throws RuntimeException;

}
