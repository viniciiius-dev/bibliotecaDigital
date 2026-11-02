package com.viniciiiusdev.fateclivros.service.impl;

import com.viniciiiusdev.fateclivros.dtos.AddReservationDTO;
import com.viniciiiusdev.fateclivros.entity.Book;
import com.viniciiiusdev.fateclivros.entity.Reservation;
import com.viniciiiusdev.fateclivros.entity.ReservationStatus;
import com.viniciiiusdev.fateclivros.entity.User;
import com.viniciiiusdev.fateclivros.repository.ReservationRepository;
import com.viniciiiusdev.fateclivros.service.BookService;
import com.viniciiiusdev.fateclivros.service.ReservationService;
import com.viniciiiusdev.fateclivros.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final UserService userService;
    private final BookService bookService;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(UserService userService, BookService bookService, ReservationRepository reservationRepository){
        this.userService = userService;
        this.bookService = bookService;
        this.reservationRepository = reservationRepository;
    }


    @Override
    @Transactional
    public void addReservation(AddReservationDTO addReservationDTO){
        User user = userService.getUser(addReservationDTO.userId());
        Book book = bookService.getBookByIsbn(addReservationDTO.isbn());

        book.reserve();
        Reservation reservation = new Reservation(user, book);

        reservationRepository.save(reservation);

    }

    @Transactional
    @Override
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.getReferenceById(reservationId);
        reservation.cancel();
    }
    @Transactional
    @Override
    public void completeReservation(Long reservationId) {
        Reservation reservation = reservationRepository.getReferenceById(reservationId);
        reservation.complete();
    }
    @Transactional
    @Override
    public void markAsReadyForPickup(Long reservationId) {
        Reservation reservation = reservationRepository.getReferenceById(reservationId);
        reservation.markAsReadyForPickup();
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        return reservationRepository.getReferenceById(reservationId);
    }

    @Override
    public List<Reservation> getReservationsByUser(Long userId) {
        User user = userService.getUser(userId);
        return reservationRepository.findByUser(user);
    }



    @Override
    public List<Reservation> findByUserIdAndReservationStatus(Long userId, ReservationStatus reservationStatus) {
        User user = userService.getUser(userId);
        return reservationRepository.findByUserAndReservationStatus(user, reservationStatus);
    }

    @Override
    public List<Reservation> findByUserOrderByExpirationDateDesc(Long userId) {
        User user = userService.getUser(userId);
        return reservationRepository.findByUserOrderByExpirationDateDesc(user);
    }

    @Override
    public List<Reservation> findByUserOrderByExpirationDateAsc(Long userId) {
        User user = userService.getUser(userId);
        return reservationRepository.findByUserOrderByExpirationDateAsc(user);
    }
}
