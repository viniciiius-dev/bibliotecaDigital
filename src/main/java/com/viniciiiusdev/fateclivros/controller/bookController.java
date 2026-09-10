package com.viniciiiusdev.fateclivros.controller;


import com.viniciiiusdev.fateclivros.dtos.AddBookDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class bookController {

    @GetMapping
    public void getBooks(){

    }

    @PostMapping
    public void addBooks(@RequestBody AddBookDTO bookDto){

    }

}
