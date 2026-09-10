package com.viniciiiusdev.fateclivros.entity;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Books")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long livro_id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String editora;

    @Column(nullable = false)
    private String idioma;

    @Column(nullable = false)
    private int ano;

    @Column(nullable = false)
    private int paginas;

    @Column(nullable = false, unique = true)
    private int isbn;

    @Column(nullable = false)
    @ManyToMany
    private Set<Usuario> user_id;


    public Livro() {
    }

    public Long getLivro_id() {
        return livro_id;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getAno() {
        return ano;
    }

    public int getPaginas() {
        return paginas;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
