package com.viniciiiusdev.fateclivros.controller;

import com.viniciiiusdev.fateclivros.dtos.AddReservationDTO;
import com.viniciiiusdev.fateclivros.dtos.AddUserDTO;
import com.viniciiiusdev.fateclivros.entity.User;
import com.viniciiiusdev.fateclivros.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
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


}
