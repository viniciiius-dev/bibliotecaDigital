package com.viniciiiusdev.fateclivros.controller;

import com.viniciiiusdev.fateclivros.dtos.AddFavoriteBookDTO;
import com.viniciiiusdev.fateclivros.dtos.BookListFrontendDTO;
import com.viniciiiusdev.fateclivros.dtos.UserProfileDTO;
import com.viniciiiusdev.fateclivros.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public UserProfileDTO getUser(){
        return userService.getUserProfileForFrontend();
    }

    @GetMapping("/saved")
    public List<BookListFrontendDTO> GetFavoriteBookForFrontend(){
        return userService.getFavoritesBookForFrontend();
    }

    @PostMapping("/saved")
    public void addFavoriteBook(@RequestBody AddFavoriteBookDTO addFavoriteBookDTO) throws RuntimeException {
        userService.addFavoriteBook(addFavoriteBookDTO.id());
    }

    @DeleteMapping("/saved")
    public void removeFavoriteBook(@RequestBody AddFavoriteBookDTO addFavoriteBookDTO) throws RuntimeException {
        userService.removeFavoriteBook(addFavoriteBookDTO.id());
    }



}
