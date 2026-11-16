package com.viniciiiusdev.fateclivros.service.impl;

import com.viniciiiusdev.fateclivros.Exception.ResourceNotFoundException;
import com.viniciiiusdev.fateclivros.Exception.UnauthorizedException;
import com.viniciiiusdev.fateclivros.dtos.BookListFrontendDTO;
import com.viniciiiusdev.fateclivros.dtos.BookStats;
import com.viniciiiusdev.fateclivros.dtos.UserProfileDTO;
import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.mapper.BookMapper;
import com.viniciiiusdev.fateclivros.mapper.UserMapper;
import com.viniciiiusdev.fateclivros.repository.UserRepository;
import com.viniciiiusdev.fateclivros.dtos.AddUserDTO;
import com.viniciiiusdev.fateclivros.entity.User;
import com.viniciiiusdev.fateclivros.service.BookService;
import com.viniciiiusdev.fateclivros.service.ReservationService;
import com.viniciiiusdev.fateclivros.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final UserMapper userMapper;
    private final ReservationService reservationService;
    private final AuthenticatedUserService authenticatedUserService;

    public UserServiceImplementation(UserRepository userRepository, BookService bookService, BookMapper bookMapper, UserMapper userMapper, ReservationService reservationService, AuthenticatedUserService authenticatedUserService){
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.reservationService = reservationService;
        this.bookMapper = bookMapper;
        this.userMapper = userMapper;
        this.authenticatedUserService = authenticatedUserService;
    }



    public void addUser(AddUserDTO addUserDTO){
        User newUser = new User(addUserDTO.name(), addUserDTO.registroAluno());
        userRepository.saveAndFlush(newUser);
    }



    @Override
    public User getAuthenticatedUser() throws RuntimeException {
            return authenticatedUserService.getAuthenticatedUser();
    }

    public List<BookListFrontendDTO> getFavoritesBookForFrontend(){
        Authentication authentication = getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByRegistroAluno(currentPrincipalName).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return bookMapper.toFrontendBookList(user.getFavoriteBooks());
    }

    @Transactional
    public void addFavoriteBook(Long bookId) throws RuntimeException {
        Authentication authentication = getAuthentication();
        String currentPrincipalName;
        currentPrincipalName = authentication.getName();

        User user = userRepository.findByRegistroAluno(currentPrincipalName).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        Book book = bookService.getBookById(bookId);

        user.addFavoriteBook(book);

    }
    @Transactional
    public void removeFavoriteBook(Long bookId) throws RuntimeException {
        Authentication authentication = getAuthentication();
        String currentPrincipalName;
        currentPrincipalName = authentication.getName();

        User user = userRepository.findByRegistroAluno(currentPrincipalName).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        Book book = bookService.getBookById(bookId);

        user.removeFavoriteBook(book);
    }

    public UserProfileDTO getUserProfileForFrontend(){
        User user = getAuthenticatedUser();
        BookStats statusLivro = reservationService.getBookStats(user);
        return userMapper.mapUserToUserProfile(user, statusLivro);
    }

    public Authentication getAuthentication() throws RuntimeException {
        return authenticatedUserService.getAuthentication();
    }

    public Set<Book> getFavoriteByUserId(Long userId){
        User user = userRepository.getReferenceById(userId);
        return user.getFavoriteBooks();
    }
}

