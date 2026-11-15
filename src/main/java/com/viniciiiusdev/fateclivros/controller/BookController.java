package com.viniciiiusdev.fateclivros.controller;


import com.viniciiiusdev.fateclivros.dtos.BookListJsonForFront;
import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    BookService bookService;
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookListJsonForFront> getBooks(){
        return bookService.bookListJsonForFrontEnd();
    }

    @GetMapping("/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn){
        return bookService.getBookByIsbn(isbn);
    }

}
