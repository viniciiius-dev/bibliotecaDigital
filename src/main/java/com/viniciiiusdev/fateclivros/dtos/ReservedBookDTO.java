package com.viniciiiusdev.fateclivros.dtos;

import com.viniciiiusdev.fateclivros.entity.BookGenre;
import com.viniciiiusdev.fateclivros.entity.ReservationStatus;

import java.time.LocalDateTime;

public record ReservedBookDTO(LocalDateTime DataExpiracao, Long reservaId, ReservationStatus reservaStatus, BookListFrontendDTO livro) {
}
