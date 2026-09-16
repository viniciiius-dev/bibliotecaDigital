package com.viniciiiusdev.fateclivros.service.impl;

import com.viniciiiusdev.fateclivros.dtos.AddReservationDTO;
import com.viniciiiusdev.fateclivros.entity.Reservation;
import com.viniciiiusdev.fateclivros.service.BookService;
import com.viniciiiusdev.fateclivros.service.ReservationService;
import com.viniciiiusdev.fateclivros.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final UserService userService;
    private final BookService bookService;

    public ReservationServiceImpl(UserService userService, BookService bookService){
        this.userService = userService;
        this.bookService = bookService;
    }


    @Override
    @Transactional
    public void addReservation(AddReservationDTO addReservationDTO){
//        User user = userService.getUser(addReservationDTO.userId());
//        Book book = bookService.getBookByIsbn(addReservationDTO.isbn());
//
//        book.reserve();
//
//        user.addBook(book);
//
//        bookService.save(book);
//        userService.save(user);
// depois arrumo
    }

    @Override
    public void cancelReservation(Long reservationId) {

    }

    @Override
    public void completeReservation(Long reservationId) {

    }

    @Override
    public void markAsReadyForPickup(Long reservationId) {

    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        return null;
    }

    @Override
    public List<Reservation> getReservationsByUser(Long userId) {
        return List.of();
    }
}
