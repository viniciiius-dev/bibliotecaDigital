package com.viniciiiusdev.fateclivros.service.impl;

import com.viniciiiusdev.fateclivros.dtos.BookListFrontendDTO;
import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.mapper.BookMapper;
import com.viniciiiusdev.fateclivros.repository.BookRepository;
import com.viniciiiusdev.fateclivros.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper){
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return bookRepository.getBookByIsbn(isbn);
    }

    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Override
    public List<BookListFrontendDTO> getBooksForFrontEnd() {
        List<Book> bookList = getAllBooks();
        return bookMapper.toFrontendBookList(bookList);
    }


    @Override
    public void saveBook(Book book){
        bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long bookId){
        return bookRepository.getReferenceById(bookId);
    }

}
