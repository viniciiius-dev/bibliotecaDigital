package com.viniciiiusdev.fateclivros.service;

import com.viniciiiusdev.fateclivros.dtos.BookListFrontendDTO;
import com.viniciiiusdev.fateclivros.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    List<BookListFrontendDTO> bookListJsonForFrontEnd();
    Book getBookByIsbn(String isbn);
    void saveBook(Book book);
    Book getBookById(Long bookId);
}
