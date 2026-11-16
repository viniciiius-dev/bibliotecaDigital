package com.viniciiiusdev.fateclivros.service;

import com.viniciiiusdev.fateclivros.dtos.AddUserDTO;
import com.viniciiiusdev.fateclivros.dtos.BookListFrontendDTO;
import com.viniciiiusdev.fateclivros.entity.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    void addUser(AddUserDTO userDTO);
    List<BookListFrontendDTO> getFavoritesBookForFrontend();
    void addFavoriteBook(Long bookId) throws RuntimeException;
    void removeFavoriteBook(Long bookId) throws RuntimeException;

}
