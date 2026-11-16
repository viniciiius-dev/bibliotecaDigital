package com.viniciiiusdev.fateclivros.controller;

import com.viniciiiusdev.fateclivros.dtos.AddFavoriteBookDTO;
import com.viniciiiusdev.fateclivros.dtos.AddUserDTO;
import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.entity.User;
import com.viniciiiusdev.fateclivros.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public User getUser(@RequestParam Long id){
        return userService.getUser(id);
    }

    @PostMapping
    public void addUser(@RequestBody AddUserDTO userDTO){
        userService.addUser(userDTO);
    }

    @GetMapping("/saved")
    public Set<Book> GetFavoriteBook(){
        return userService.getFavoritesBook();
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
