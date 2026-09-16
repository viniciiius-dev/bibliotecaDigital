package com.viniciiiusdev.fateclivros.entity;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false, name = "publication_year")
    private int year;

    @Column(nullable = false)
    private int pages;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private int bookStock;


    public Book() {
    }

    public Long getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getLanguage() {
        return language;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getBookStock() {
        return bookStock;
    }

    public boolean reserve(){
        if (bookStock > 0){
            this.bookStock--;
            return true;
        }
        return false;
    }

    public boolean release(){
        bookStock += 1;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }
}
