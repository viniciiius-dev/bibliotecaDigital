package com.viniciiiusdev.fateclivros.repository;

import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.entity.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book getBookByIsbn(String isbn);
    List<Book> findByGenre(BookGenre genre);
}
