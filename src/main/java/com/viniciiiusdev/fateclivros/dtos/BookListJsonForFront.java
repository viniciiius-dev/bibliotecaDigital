package com.viniciiiusdev.fateclivros.dtos;

public record BookListJsonForFront(Long id, String foto, com.viniciiiusdev.fateclivros.entity.BookGenre genero, String nome, String autor, String sinopse, String status){}
