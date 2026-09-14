package com.viniciiiusdev.fateclivros.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String registroAluno;

    public User() {
    }
    public User(String name, String registroAluno) {
        this.name = name;
        this.registroAluno = registroAluno;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getRegistroAluno() {
        return registroAluno;
    }

    public void setRegistroAluno(String registroAluno) {
        this.registroAluno = registroAluno;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(registroAluno, user.registroAluno);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(registroAluno);
    }
}
