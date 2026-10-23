package com.viniciiiusdev.fateclivros.service.impl;

import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.repository.BookRepository;
import com.viniciiiusdev.fateclivros.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return bookRepository.getBookByIsbn(isbn);
    }

    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
}
