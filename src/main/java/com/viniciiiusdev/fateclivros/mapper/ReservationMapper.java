package com.viniciiiusdev.fateclivros.mapper;

import com.viniciiiusdev.fateclivros.dtos.ReservedBookDTO;
import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.entity.Reservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationMapper {
    private final BookMapper bookMapper;
    public ReservationMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<ReservedBookDTO> mapToReservedBooks(List<Reservation> reservationList){
        List<ReservedBookDTO> reservedBookDTOS = new ArrayList<>();
        for (Reservation reservation : reservationList){
            Book book = reservation.getBook();
            reservedBookDTOS.add(new ReservedBookDTO(
                    reservation.getExpirationDate(),
                    reservation.getReservationId(),
                    reservation.getReservationStatus(),
                    bookMapper.mapSingleBookToFrontend(book)));
        }
        return reservedBookDTOS;
    }
    public ReservedBookDTO mapToReservedBook(Reservation reservation) {
        Book book = reservation.getBook();

        return new ReservedBookDTO(
                reservation.getExpirationDate(),
                reservation.getReservationId(),
                reservation.getReservationStatus(),
                bookMapper.mapSingleBookToFrontend(book)
        );
    }
}
