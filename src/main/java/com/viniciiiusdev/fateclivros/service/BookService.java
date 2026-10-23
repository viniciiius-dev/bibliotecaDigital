package com.viniciiiusdev.fateclivros.service;

import com.viniciiiusdev.fateclivros.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookByIsbn(String isbn);
}
