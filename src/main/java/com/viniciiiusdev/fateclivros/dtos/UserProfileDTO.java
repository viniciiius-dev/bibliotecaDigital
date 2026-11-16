package com.viniciiiusdev.fateclivros.dtos;

public record UserProfileDTO(Long userId, String nome, String registroAluno, String emailAcademico, BookStats metricas) {
}
