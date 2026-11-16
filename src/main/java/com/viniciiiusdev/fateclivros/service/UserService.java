package com.viniciiiusdev.fateclivros.service;

import com.viniciiiusdev.fateclivros.dtos.AddUserDTO;
import com.viniciiiusdev.fateclivros.dtos.BookListFrontendDTO;
import com.viniciiiusdev.fateclivros.dtos.UserProfileDTO;
import com.viniciiiusdev.fateclivros.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    void addUser(AddUserDTO userDTO);
    User getAuthenticatedUser() throws RuntimeException;
    List<BookListFrontendDTO> getFavoritesBookForFrontend();
    void addFavoriteBook(Long bookId) throws RuntimeException;
    void removeFavoriteBook(Long bookId) throws RuntimeException;
    UserProfileDTO getUserProfileForFrontend();
    Authentication getAuthentication() throws RuntimeException;
}
