package com.viniciiiusdev.fateclivros.dtos;

import com.viniciiiusdev.fateclivros.entity.BookGenre;

public record BookListFrontendDTO(Long id, String foto, BookGenre genero, String nome, String autor, String sinopse, String status, String editora, String idioma, int ano, int paginas, String isbn, int estoque){
}

