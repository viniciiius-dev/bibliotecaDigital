package com.viniciiiusdev.fateclivros.repository;

import com.viniciiiusdev.fateclivros.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book getBookByIsbn(String isbn);
}
